package com.carlosrvff.bsreader.converter;

import com.carlosrvff.bsreader.helper.BankStatementUtils;
import com.carlosrvff.bsreader.helper.RevolutStatementUtils;

public class RevolutConverterTest extends BankConverterTest {

  @Override
  protected BankConverter getBankConverter() {
    return new RevolutConverter();
  }

  @Override
  protected BankStatementUtils getBankStatementUtils() {
    return new RevolutStatementUtils();
  }
}
