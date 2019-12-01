package com.github.carlosrvff.bsreader.converter;

import com.github.carlosrvff.bsreader.exception.InvalidStatementException;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

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

  public abstract boolean isDebitValue(String value);
}
