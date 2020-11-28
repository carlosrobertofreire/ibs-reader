package com.carlosrvff.bsreader.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.carlosrvff.bsreader.domain.Statement;
import com.carlosrvff.bsreader.domain.statement.Balance;
import com.carlosrvff.bsreader.helper.BankStatementUtils;
import com.carlosrvff.bsreader.helper.ItauSiteStatementUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ItauSiteStatementConverterTest extends StatementConverterTest {

  private ItauSiteStatementConverter target;
  private ItauSiteStatementUtils utils;

  @BeforeEach
  protected void setUp() {
    super.setUp();
    target = (ItauSiteStatementConverter) getStatementConverter();
    utils = (ItauSiteStatementUtils) getBankStatementUtils();
  }

  @Override
  protected StatementConverter getStatementConverter() {
    return new ItauSiteStatementConverter();
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
