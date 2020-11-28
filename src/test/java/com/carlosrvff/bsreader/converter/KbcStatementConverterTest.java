package com.carlosrvff.bsreader.converter;

import com.carlosrvff.bsreader.helper.BankStatementUtils;
import com.carlosrvff.bsreader.helper.KbcStatementUtils;
import org.junit.jupiter.api.BeforeEach;

class KbcStatementConverterTest extends StatementConverterTest {

  private KbcStatementConverter target;
  private KbcStatementUtils utils;

  @BeforeEach
  protected void setUp() {
    super.setUp();
    target = (KbcStatementConverter) getStatementConverter();
    utils = (KbcStatementUtils) getBankStatementUtils();
  }

  @Override
  protected StatementConverter getStatementConverter() {
    return new KbcStatementConverter();
  }

  @Override
  protected BankStatementUtils getBankStatementUtils() {
    return new KbcStatementUtils();
  }
}
