package com.carlosrvff.bsreader.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.carlosrvff.bsreader.domain.Debit;
import com.carlosrvff.bsreader.domain.Statement;
import com.carlosrvff.bsreader.helper.BankStatementUtils;
import com.carlosrvff.bsreader.helper.CheetahStatementUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CheetahConverterTest extends BankConverterTest {

  private CheetahConverter target;
  private CheetahStatementUtils utils;

  @BeforeEach
  protected void setUp() {
    super.setUp();
    target = (CheetahConverter) getBankConverter();
    utils = (CheetahStatementUtils) getBankStatementUtils();
  }

  @Override
  protected BankConverter getBankConverter() {
    return new CheetahConverter();
  }

  @Override
  protected BankStatementUtils getBankStatementUtils() {
    return new CheetahStatementUtils();
  }

  @Test
  void toStatementWhenTextIsFeeDebit() {
    String textFixture = utils.createFeeDebitStatementText();
    Debit expectedFeeDebit = utils.createFeeDebit(textFixture);

    Statement statement = target.toStatement(textFixture);

    assertTrue(statement instanceof Debit);
    assertEquals(expectedFeeDebit, statement);
  }
}
