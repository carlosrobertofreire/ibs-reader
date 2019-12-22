package com.github.carlosrvff.bsreader.converter;

import com.github.carlosrvff.bsreader.domain.Balance;
import com.github.carlosrvff.bsreader.domain.Credit;
import com.github.carlosrvff.bsreader.domain.Debit;
import com.github.carlosrvff.bsreader.domain.Statement;
import com.github.carlosrvff.bsreader.exception.InvalidStatementException;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

public class ItauSiteConverter extends BankConverter {

  @Override
  public Statement toStatement(@NonNull String line) throws InvalidStatementException {
    validate(line);
    String[] parts = line.split("\t");
    if (parts.length < 6) {
      throw new InvalidStatementException("Incorrect numbers of fields.", line);
    }
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
}
