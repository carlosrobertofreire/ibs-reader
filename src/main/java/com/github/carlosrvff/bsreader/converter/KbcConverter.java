package com.github.carlosrvff.bsreader.converter;

import com.github.carlosrvff.bsreader.domain.Statement;
import com.github.carlosrvff.bsreader.exception.InvalidStatementException;
import lombok.NonNull;

public class KbcConverter extends BankConverter {

  public static final char SEPARATOR = '\t';
  public static final char CURRENCY_SYMBOL = '€';

  @Override
  public Statement toStatement(@NonNull String text) throws InvalidStatementException {
    validate(text);
    return null;
  }

  @Override
  public String getHeader() {
    return "Date\tTransaction\tMoney In\tMoney Out\tBalance";
  }
}
