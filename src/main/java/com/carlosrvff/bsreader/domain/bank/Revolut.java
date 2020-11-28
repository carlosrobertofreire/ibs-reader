package com.carlosrvff.bsreader.domain.bank;

import com.carlosrvff.bsreader.converter.RevolutStatementConverter;
import com.carlosrvff.bsreader.converter.StatementConverter;
import com.carlosrvff.bsreader.domain.Bank;

public class Revolut extends Bank {

  public Revolut(String statementsText) {
    super(statementsText);
  }

  public static String getHeader() {
    return "Completed Date;Reference;Paid Out (EUR);Paid In (EUR);Exchange Out;Exchange In; Balance (EUR);Exchange Rate;Category";
  }

  @Override
  protected StatementConverter getStatementConverter() {
    return new RevolutStatementConverter();
  }
}
