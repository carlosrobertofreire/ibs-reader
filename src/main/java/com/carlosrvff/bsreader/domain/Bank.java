package com.carlosrvff.bsreader.domain;

import com.carlosrvff.bsreader.converter.StatementConverter;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

@Log4j2
public abstract class Bank {

  private String statementsText;

  public Bank() {}

  public Bank(String statementsText) {
    this.statementsText = statementsText;
  }

  protected abstract StatementConverter getStatementConverter();

  public List<Statement> getStatements() {
    String[] lines = statementsText.split(System.lineSeparator());
    return getStatements(lines);
  }

  protected List<Statement> getStatements(@NonNull String[] lines) {
    List<Statement> result = new ArrayList<>();
    for (String line : lines) {
      processLine(result, line);
    }
    return result;
  }

  protected void processLine(List<Statement> result, String line) {
    try {
      result.add(getStatementConverter().toStatement(line));
    } catch (IllegalArgumentException e) {
      log.fatal(e.getMessage());
      if (e.getCause() != null) {
        log.fatal("  Original exception: " + e.getCause().getMessage());
      }
    }
  }
}
