package com.carlosrvff.bsreader.converter;

import com.carlosrvff.bsreader.domain.Statement;
import org.apache.commons.lang3.StringUtils;

public abstract class StatementConverter {

  public Statement toStatement(String line) {
    validateLine(line);
    String[] parts = line.split(getSeparator());
    validateParts(line, parts);
    return toStatement(parts, line);
  }

  protected void validateLine(String text) {
    if (StringUtils.isBlank(text)) {
      throw new IllegalArgumentException("Statement cannot be blank: " + text);
    }
  }

  public abstract String getSeparator();

  private void validateParts(String line, String[] parts) {
    if (arePartsInvalid(parts)) {
      throw new IllegalArgumentException("Incorrect numbers of fields: " + line);
    }
  }

  protected abstract boolean arePartsInvalid(String[] parts);

  protected abstract Statement toStatement(String[] parts, String line);

  protected boolean isDebitValue(String value) {
    return value.charAt(0) == getDebitSymbol();
  }

  protected char getDebitSymbol() {
    return '-';
  }

}
