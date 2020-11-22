package com.carlosrvff.bsreader.converter;

import com.carlosrvff.bsreader.domain.Credit;
import com.carlosrvff.bsreader.domain.Debit;
import com.carlosrvff.bsreader.domain.Statement;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

public class KbcConverter extends BankConverter {

  public static final char CURRENCY_SYMBOL = '€';
  public static final int TRANSACTION_LINES_QTT = 3;

  @Override
  public Statement toStatement(@NonNull String line) {
    validate(line);
    String[] parts = line.split(getSeparator());
    if (parts.length < 5) {
      throw new IllegalArgumentException("Incorrect numbers of fields: " + line);
    }
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
    value = StringUtils.remove(value, DEBIT_SYMBOL);
    value = StringUtils.remove(value, CURRENCY_SYMBOL);
    return value;
  }

  @Override
  public List<Statement> toStatements(@NonNull String[] lines) {
    List<Statement> result = new ArrayList<>();
    int index = 0;
    while (index <= lines.length - TRANSACTION_LINES_QTT) {
      String kbcTransactionLine =
          new StringBuilder()
              .append(lines[index++])
              .append(getSeparator())
              .append(lines[index++])
              .append(getSeparator())
              .append(lines[index++])
              .toString();
      processLine(result, kbcTransactionLine);
    }
    return result;
  }

  @Override
  public String getHeader() {
    return "Date\tTransaction\tMoney In\tMoney Out\tBalance";
  }

  @Override
  protected String getSeparator() {
    return "\t";
  }
}
