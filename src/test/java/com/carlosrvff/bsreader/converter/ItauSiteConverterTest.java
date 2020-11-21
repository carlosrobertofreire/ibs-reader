package com.carlosrvff.bsreader.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.carlosrvff.bsreader.domain.Balance;
import com.carlosrvff.bsreader.domain.Statement;
import com.carlosrvff.bsreader.helper.BankStatementUtils;
import com.carlosrvff.bsreader.helper.ItauSiteStatementUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItauSiteConverterTest extends BankConverterTest {

  private ItauSiteConverter target;
  private ItauSiteStatementUtils utils;

  @BeforeEach
  protected void setUp() {
    super.setUp();
    target = (ItauSiteConverter) getBankConverter();
    utils = (ItauSiteStatementUtils) getBankStatementUtils();
  }

  @Override
  protected BankConverter getBankConverter() {
    return new ItauSiteConverter();
  }

  @Override
  protected BankStatementUtils getBankStatementUtils() {
    return new ItauSiteStatementUtils();
  }

  @Test
  void toStatementWhenTextIsBalance() {
    String textFixture = utils.createBalanceStatementText();
    Balance expectedBalance = utils.createBalanceStatement(textFixture);

    Statement statement = target.toStatement(textFixture);

    assertTrue(statement instanceof Balance);
    assertEquals(expectedBalance, statement);
  }
}
