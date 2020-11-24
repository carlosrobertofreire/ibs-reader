package com.carlosrvff.bsreader.domain.bank;

import com.carlosrvff.bsreader.converter.ItauCsvStatementConverter;
import com.carlosrvff.bsreader.converter.StatementConverter;
import com.carlosrvff.bsreader.domain.Bank;

public class ItauCsv extends Bank {

  public ItauCsv(String statementsText) {
    super(statementsText);
  }

  public static String getHeader() {
    return "data;detalhe;valor";
  }

  @Override
  protected StatementConverter getStatementConverter() {
    return new ItauCsvStatementConverter();
  }
}
