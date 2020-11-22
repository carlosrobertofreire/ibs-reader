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

  public Statement toStatement(@NonNull String line) {
    validateLine(line);
    String[] parts = line.split(getSeparator());
    validateParts(line, parts);
    return toStatement(parts, line);
  }

  protected void validateLine(@NonNull String text) {
    if (StringUtils.isBlank(text)) {
      throw new IllegalArgumentException("Statement cannot be blank: " + text);
    }
    if (text.equalsIgnoreCase(getHeader())) {
      throw new IllegalArgumentException("Header cannot be considered a Statement: " + text);
    }
  }

  public abstract String getHeader();

  protected abstract String getSeparator();

  private void validateParts(@NonNull String line, String[] parts) {
    if (arePartsInvalid(parts)) {
      throw new IllegalArgumentException("Incorrect numbers of fields: " + line);
    }
  }

  protected abstract boolean arePartsInvalid(String[] parts);

  protected abstract Statement toStatement(String[] parts, String line);

  protected boolean isDebitValue(String value) {
    return value.charAt(0) == DEBIT_SYMBOL;
  }
}
