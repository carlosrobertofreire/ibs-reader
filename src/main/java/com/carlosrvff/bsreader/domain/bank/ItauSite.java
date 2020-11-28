package com.carlosrvff.bsreader.domain.bank;

import com.carlosrvff.bsreader.converter.ItauSiteStatementConverter;
import com.carlosrvff.bsreader.converter.StatementConverter;
import com.carlosrvff.bsreader.domain.Bank;

public class ItauSite extends Bank {

  public ItauSite(String statementsText) {
    super(statementsText);
  }

  public static String getHeader() {
    return "Data\t\t\tLançamento\t\tValor (R$)\t\tSaldo (R$)\t";
  }

  @Override
  protected StatementConverter getStatementConverter() {
    return new ItauSiteStatementConverter();
  }
}
