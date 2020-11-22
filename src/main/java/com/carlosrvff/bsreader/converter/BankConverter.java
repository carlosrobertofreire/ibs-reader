package com.carlosrvff.bsreader.converter;

import com.carlosrvff.bsreader.domain.Statement;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

@Log4j2
public abstract class BankConverter {

  public static final char DEBIT_SYMBOL = '-';

  protected void validate(@NonNull String text) {
    if (StringUtils.isBlank(text)) {
      throw new IllegalArgumentException("Statement cannot be blank: " + text);
    }
    if (text.equalsIgnoreCase(getHeader())) {
      throw new IllegalArgumentException("Header cannot be considered a Statement: " + text);
    }
  }

  protected boolean isDebitValue(String value) {
    return value.charAt(0) == DEBIT_SYMBOL;
  }

  public List<Statement> toStatements(@NonNull String content) {
    String[] lines = content.split(System.lineSeparator());
    return toStatements(lines);
  }

  protected List<Statement> toStatements(@NonNull String[] lines) {
    List<Statement> result = new ArrayList<>();
    for (String line : lines) {
      processLine(result, line);
    }
    return result;
  }

  protected void processLine(List<Statement> result, String line) {
    try {
      result.add(toStatement(line));
    } catch (IllegalArgumentException e) {
      log.fatal(e.getMessage());
      if (e.getCause() != null) {
        log.fatal("  Original exception: " + e.getCause().getMessage());
      }
    }
  }

  public abstract String getHeader();

  protected abstract String getSeparator();

  protected abstract Statement toStatement(@NonNull String line);

}
