package com.carlosrvff.bsreader.domain.bank;

import com.carlosrvff.bsreader.converter.CheetahStatementConverter;
import com.carlosrvff.bsreader.converter.StatementConverter;
import com.carlosrvff.bsreader.domain.Bank;

public class Cheetah extends Bank {

  public Cheetah(String statementsText) {
    super(statementsText);
  }

  public static String getHeader() {
    return "Date, Transaction Type, Merchant, Amount, Fee, Result";
  }

  @Override
  protected StatementConverter getStatementConverter() {
    return new CheetahStatementConverter();
  }
}