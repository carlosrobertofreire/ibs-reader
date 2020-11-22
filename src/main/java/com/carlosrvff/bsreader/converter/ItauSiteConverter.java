package com.carlosrvff.bsreader.converter;

import com.carlosrvff.bsreader.domain.Balance;
import com.carlosrvff.bsreader.domain.Credit;
import com.carlosrvff.bsreader.domain.Debit;
import com.carlosrvff.bsreader.domain.Statement;
import org.apache.commons.lang3.StringUtils;

public class ItauSiteConverter extends BankConverter {

  @Override
  protected boolean arePartsInvalid(String[] parts) {
    return parts.length < 6;
  }

  @Override
  protected Statement toStatement(String[] parts, String line) {
    if (parts.length >= 8) {
      return Balance.builder().date(parts[0]).value(parts[7]).originalText(line).build();
    }
    String date = parts[0];
    String details = parts[3].trim();
    String value = parts[5];
    if (isDebitValue(value)) {
      return Debit.builder()
          .date(date)
          .store(details)
          .value(removeDebitSymbol(value))
          .originalText(line)
          .build();
    } else {
      return Credit.builder().date(date).from(details).value(value).originalText(line).build();
    }
  }

  @Override
  public boolean isDebitValue(String value) {
    return value.charAt(value.length() - 1) == DEBIT_SYMBOL;
  }

  private String removeDebitSymbol(String value) {
    return StringUtils.chop(value);
  }

  @Override
  public String getHeader() {
    return "Data\t\t\tLançamento\t\tValor (R$)\t\tSaldo (R$)\t";
  }

  @Override
  protected String getSeparator() {
    return "\t";
  }
}
