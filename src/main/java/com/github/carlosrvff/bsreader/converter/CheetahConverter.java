package com.github.carlosrvff.bsreader.converter;

import com.github.carlosrvff.bsreader.domain.Statement;
import com.github.carlosrvff.bsreader.exception.InvalidStatementException;
import lombok.NonNull;

public class CheetahConverter extends BankConverter {

  @Override
  public Statement toStatement(@NonNull String text) throws InvalidStatementException {
    validate(text);
    return null;
  }

  @Override
  public String getHeader() {
    return "Date, Transaction Type, Merchant, Amount, Fee, Result";
  }
}
