package com.carlosrvff.bsreader.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.carlosrvff.bsreader.domain.Statement;
import com.carlosrvff.bsreader.domain.statement.Credit;
import com.carlosrvff.bsreader.domain.statement.Debit;
import com.carlosrvff.bsreader.helper.BankStatementUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

abstract class StatementConverterTest {

  private StatementConverter target;
  private BankStatementUtils utils;

  protected abstract StatementConverter getStatementConverter();

  protected abstract BankStatementUtils getBankStatementUtils();

  @BeforeEach
  protected void setUp() {
    this.target = getStatementConverter();
    this.utils = getBankStatementUtils();
  }

  @Test
  protected void toStatementWhenTextIsDebit() {
    String textFixture = utils.createDebitStatementText();
    Debit expectedFeeDebit = utils.createDebitStatement(textFixture);

    Statement statement = target.toStatement(textFixture);

    assertTrue(statement instanceof Debit);
    assertEquals(expectedFeeDebit, statement);
  }

  @Test
  protected void toStatementWhenTextIsCredit() {
    String textFixture = utils.createCreditStatementText();
    Credit expectedCredit = utils.createCreditStatement(textFixture);

    Statement statement = target.toStatement(textFixture);

    assertTrue(statement instanceof Credit);
    assertEquals(expectedCredit, statement);
  }

  @Test
  protected void toStatementWhenTextIsEmpty() {
    String textFixture = "";
    assertThrows(IllegalArgumentException.class, () -> target.toStatement(textFixture));
  }

  @Test
  protected void toStatementWhenTextIsInvalid() {
    String textFixture = "InvalidText";
    assertThrows(IllegalArgumentException.class, () -> target.toStatement(textFixture));
  }

  @Test
  protected void toStatementWhenTextIsNull() {
    assertThrows(IllegalArgumentException.class, () -> target.toStatement(null));
  }

}
