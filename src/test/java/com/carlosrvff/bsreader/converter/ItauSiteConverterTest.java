package com.carlosrvff.bsreader.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.carlosrvff.bsreader.domain.Balance;
import com.carlosrvff.bsreader.domain.Credit;
import com.carlosrvff.bsreader.domain.Debit;
import com.carlosrvff.bsreader.domain.Statement;
import com.carlosrvff.bsreader.exception.InvalidStatementException;
import com.carlosrvff.bsreader.helper.ItauSiteStatementUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItauSiteConverterTest {

  private ItauSiteConverter target;
  private ItauSiteStatementUtils utils;

  @BeforeEach
  void setUp() {
    target = new ItauSiteConverter();
    utils = new ItauSiteStatementUtils();
  }

  @Test
  void toStatementWhenTextIsDebit() throws InvalidStatementException {
    String textFixture = utils.createDebitStatementText();
    Debit expectedDebit = utils.createDebitStatement(textFixture);

    Statement statement = target.toStatement(textFixture);

    assertTrue(statement instanceof Debit);
    assertEquals(expectedDebit, statement);
  }

  @Test
  void toStatementWhenTextIsBalance() throws InvalidStatementException {
    String textFixture = utils.createBalanceStatementText();
    Balance expectedBalance = utils.createBalanceStatement(textFixture);

    Statement statement = target.toStatement(textFixture);

    assertTrue(statement instanceof Balance);
    assertEquals(expectedBalance, statement);
  }

  @Test
  void toStatementWhenTextIsCredit() throws InvalidStatementException {
    String textFixture = utils.createCreditStatementText();
    Credit expectedCredit = utils.createCreditStatement(textFixture);

    Statement statement = target.toStatement(textFixture);

    assertTrue(statement instanceof Credit);
    assertEquals(expectedCredit, statement);
  }

  @Test
  void toStatementWhenTextIsEmpty() {
    String textFixture = "";

    assertThrows(InvalidStatementException.class, () -> target.toStatement(textFixture));
  }

  @Test
  void toStatementWhenTextIsInvalid() {
    String textFixture = "InvalidText";

    assertThrows(InvalidStatementException.class, () -> target.toStatement(textFixture));
  }

  @Test
  void toStatementWhenTextIsNull() {
    assertThrows(NullPointerException.class, () -> target.toStatement(null));
  }

  @Test
  void toStatementWhenTextIsHeader() {
    String textFixture = target.getHeader();

    assertThrows(InvalidStatementException.class, () -> target.toStatement(textFixture));
  }

  @Test
  void getHeader() {
    assertTrue(StringUtils.isNotBlank(target.getHeader()));
  }
}
