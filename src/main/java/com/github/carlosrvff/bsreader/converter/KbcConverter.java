package com.github.carlosrvff.bsreader.converter;

import com.github.carlosrvff.bsreader.domain.Statement;
import com.github.carlosrvff.bsreader.exception.InvalidStatementException;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;

public class KbcConverter extends BankConverter {

  public static final char SEPARATOR = '\t';
  public static final char CURRENCY_SYMBOL = '€';
  public static final int TRANSACTION_LINES_QTT = 3;

  @Override
  public Statement toStatement(@NonNull String line) throws InvalidStatementException {
    validate(line);
    return null;
  }

  @Override
  public List<Statement> toStatements(@NonNull String[] lines) {
    List<Statement> result = new ArrayList<>();
    int index = 0;
    while (index <= lines.length - TRANSACTION_LINES_QTT) {
      String kbcTransactionLine =
          new StringBuilder()
              .append(lines[index++])
              .append(SEPARATOR)
              .append(lines[index++])
              .append(SEPARATOR)
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
}
