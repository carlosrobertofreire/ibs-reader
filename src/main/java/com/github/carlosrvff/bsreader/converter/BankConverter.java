package com.github.carlosrvff.bsreader.converter;

import com.github.carlosrvff.bsreader.domain.Statement;
import com.github.carlosrvff.bsreader.exception.InvalidStatementException;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

@Log4j2
public abstract class BankConverter implements StatementConverter {

  public static final char DEBIT_SYMBOL = '-';

  public void validate(@NonNull String text) throws InvalidStatementException {
    if (StringUtils.isBlank(text)) {
      throw new InvalidStatementException("Statement cannot be blank.", text);
    }
    if (text.equalsIgnoreCase(getHeader())) {
      throw new InvalidStatementException("Header cannot be considered a Statement.", text);
    }
  }

  public boolean isDebitValue(String value) {
    return value.charAt(0) == DEBIT_SYMBOL;
  }

  @Override
  public List<Statement> toStatements(@NonNull String content) {
    String[] lines = content.split(System.lineSeparator());
    return toStatements(lines);
  }

  @Override
  public List<Statement> toStatements(@NonNull String[] lines) {
    List<Statement> result = new ArrayList<>();
    for (String line : lines) {
      processLine(result, line);
    }
    return result;
  }

  public void processLine(List<Statement> result, String line) {
    try {
      result.add(toStatement(line));
    } catch (InvalidStatementException e) {
      log.fatal(e.getMessage());
      if (e.getCause() != null) {
        log.fatal("  Original exception: " + e.getCause().getMessage());
      }
    }
  }
}
