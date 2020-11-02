package com.carlosrvff.bsreader.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.carlosrvff.bsreader.domain.Credit;
import com.carlosrvff.bsreader.domain.Debit;
import com.carlosrvff.bsreader.domain.Statement;
import com.carlosrvff.bsreader.exception.InvalidStatementException;
import com.carlosrvff.bsreader.helper.BankStatementUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

abstract class BankConverterTest {

  private BankConverter target;
  private BankStatementUtils utils;

  protected abstract BankConverter getBankConverter();

  protected abstract BankStatementUtils getBankStatementUtils();

  @BeforeEach
  protected void setUp() {
    this.target = getBankConverter();
    this.utils = getBankStatementUtils();
  }

  @Test
  protected void toStatementWhenTextIsDebit() throws InvalidStatementException {
    String textFixture = utils.createDebitStatementText();
    Debit expectedFeeDebit = utils.createDebitStatement(textFixture);

    Statement statement = target.toStatement(textFixture);

    assertTrue(statement instanceof Debit);
    assertEquals(expectedFeeDebit, statement);
  }

  @Test
  protected void toStatementWhenTextIsCredit() throws InvalidStatementException {
    String textFixture = utils.createCreditStatementText();
    Credit expectedCredit = utils.createCreditStatement(textFixture);

    Statement statement = target.toStatement(textFixture);

    assertTrue(statement instanceof Credit);
    assertEquals(expectedCredit, statement);
  }

  @Test
  protected void toStatementWhenTextIsEmpty() {
    String textFixture = "";
    assertThrows(InvalidStatementException.class, () -> target.toStatement(textFixture));
  }

  @Test
  protected void toStatementWhenTextIsInvalid() {
    String textFixture = "InvalidText";
    assertThrows(InvalidStatementException.class, () -> target.toStatement(textFixture));
  }

  @Test
  protected void toStatementWhenTextIsNull() {
    assertThrows(NullPointerException.class, () -> target.toStatement(null));
  }

  @Test
  protected void toStatementWhenTextIsHeader() {
    String textFixture = target.getHeader();
    assertThrows(InvalidStatementException.class, () -> target.toStatement(textFixture));
  }

  @Test
  protected void getHeader() {
    assertTrue(StringUtils.isNotBlank(target.getHeader()));
  }
}
