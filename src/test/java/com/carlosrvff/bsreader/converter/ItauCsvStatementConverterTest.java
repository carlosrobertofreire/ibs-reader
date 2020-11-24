package com.carlosrvff.bsreader.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.carlosrvff.bsreader.domain.Statement;
import com.carlosrvff.bsreader.domain.statement.Balance;
import com.carlosrvff.bsreader.helper.BankStatementUtils;
import com.carlosrvff.bsreader.helper.ItauCsvStatementUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItauCsvStatementConverterTest extends StatementConverterTest{

  private ItauCsvStatementConverter target;
  private ItauCsvStatementUtils utils;

  @BeforeEach
  protected void setUp() {
    super.setUp();
    target = (ItauCsvStatementConverter) getStatementConverter();
    utils = (ItauCsvStatementUtils) getBankStatementUtils();
  }

  @Override
  protected StatementConverter getStatementConverter() {
    return new ItauCsvStatementConverter();
  }

  @Override
  protected BankStatementUtils getBankStatementUtils() {
    return new ItauCsvStatementUtils();
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