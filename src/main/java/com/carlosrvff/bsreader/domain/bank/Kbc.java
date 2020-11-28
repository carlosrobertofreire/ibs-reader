package com.carlosrvff.bsreader.domain.bank;

import com.carlosrvff.bsreader.converter.KbcStatementConverter;
import com.carlosrvff.bsreader.converter.StatementConverter;
import com.carlosrvff.bsreader.domain.Bank;
import com.carlosrvff.bsreader.domain.Statement;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;

public class Kbc extends Bank {

  public static final int TRANSACTION_LINES_QTT = 3;

  public Kbc() {}

  public Kbc(String statementsText) {
    super(statementsText);
  }

  public static String getHeader() {
    return "Date\tTransaction\tMoney In\tMoney Out\tBalance";
  }

  @Override
  protected StatementConverter getStatementConverter() {
    return new KbcStatementConverter();
  }

  @Override
  public List<Statement> getStatements(@NonNull String[] lines) {
    List<Statement> result = new ArrayList<>();
    int index = 0;
    while (index <= lines.length - TRANSACTION_LINES_QTT) {
      String separator = getStatementConverter().getSeparator();
      String kbcTransactionLine =
          new StringBuilder()
              .append(lines[index++])
              .append(separator)
              .append(lines[index++])
              .append(separator)
              .append(lines[index++])
              .toString();
      processLine(result, kbcTransactionLine);
    }
    return result;
  }
}
