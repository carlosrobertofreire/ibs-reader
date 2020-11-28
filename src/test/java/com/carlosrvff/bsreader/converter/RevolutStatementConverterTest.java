package com.carlosrvff.bsreader.converter;

import com.carlosrvff.bsreader.helper.BankStatementUtils;
import com.carlosrvff.bsreader.helper.RevolutStatementUtils;

class RevolutStatementConverterTest extends StatementConverterTest {

  @Override
  protected StatementConverter getStatementConverter() {
    return new RevolutStatementConverter();
  }

  @Override
  protected BankStatementUtils getBankStatementUtils() {
    return new RevolutStatementUtils();
  }
}
