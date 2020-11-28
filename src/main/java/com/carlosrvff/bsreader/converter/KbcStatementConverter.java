package com.carlosrvff.bsreader.converter;

import com.carlosrvff.bsreader.domain.Statement;
import com.carlosrvff.bsreader.domain.statement.Credit;
import com.carlosrvff.bsreader.domain.statement.Debit;
import org.apache.commons.lang3.StringUtils;

public class KbcStatementConverter extends StatementConverter {

  public static final char CURRENCY_SYMBOL = '€';

  @Override
  public String getSeparator() {
    return "\t";
  }

  @Override
  protected boolean arePartsInvalid(String[] parts) {
    return parts.length < 5;
  }

  @Override
  protected Statement toStatement(String[] parts, String line) {
    String date = parts[0];
    String details = parts[2];
    String value = parts[3];
    if (isDebitValue(value)) {
      return Debit.builder()
          .date(date)
          .store(details)
          .value(removeExtraSymbols(value))
          .originalText(line)
          .build();
    } else {
      return Credit.builder().date(date).from(details).value(removeExtraSymbols(value)).originalText(line).build();
    }
  }

  private String removeExtraSymbols(String value) {
    value = StringUtils.remove(value, getDebitSymbol());
    value = StringUtils.remove(value, CURRENCY_SYMBOL);
    return value;
  }

}
