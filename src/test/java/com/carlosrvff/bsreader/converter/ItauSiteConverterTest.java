package com.carlosrvff.bsreader.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.carlosrvff.bsreader.domain.Balance;
import com.carlosrvff.bsreader.domain.Credit;
import com.carlosrvff.bsreader.domain.Debit;
import com.carlosrvff.bsreader.domain.Statement;
import com.carlosrvff.bsreader.exception.InvalidStatementException;
import com.carlosrvff.bsreader.helper.ItauStatementUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItauSiteConverterTest {

  private ItauSiteConverter target;

  @BeforeEach
  void setUp() {
    target = new ItauSiteConverter();
  }

  @Test
  void toStatementWhenTextIsDebit() throws InvalidStatementException {
    String textFixture = createDebitStatementText();
    Debit expectedDebit = ItauStatementUtils.createDebitStatement(textFixture);

    Statement statement = target.toStatement(textFixture);

    assertTrue(statement instanceof Debit);
    assertEquals(expectedDebit, statement);
  }

  private String createDebitStatementText() {
    return new StringBuilder(ItauStatementUtils.DATE_FIXTURE)
        .append("\t\t\t")
        .append(ItauStatementUtils.DEBIT_DETAIL_FIXTURE)
        .append("\t\t")
        .append(ItauStatementUtils.VALUE_FIXTURE)
        .append("-")
        .append("\t\t\t")
        .toString();
  }

  @Test
  void toStatementWhenTextIsBalance() throws InvalidStatementException {
    String textFixture = createBalanceStatementText();
    Balance expectedBalance = ItauStatementUtils.createBalanceStatement(textFixture);

    Statement statement = target.toStatement(textFixture);

    assertTrue(statement instanceof Balance);
    assertEquals(expectedBalance, statement);
  }

  private String createBalanceStatementText() {
    return new StringBuilder(ItauStatementUtils.DATE_FIXTURE)
        .append("\t\t\t")
        .append(ItauStatementUtils.BALANCE_DETAIL_FIXTURE)
        .append("\t\t\t\t")
        .append(ItauStatementUtils.VALUE_FIXTURE)
        .append("\t")
        .toString();
  }

  @Test
  void toStatementWhenTextIsCredit() throws InvalidStatementException {
    String textFixture = createCreditStatementText();
    Credit expectedCredit = ItauStatementUtils.createCreditStatement(textFixture);

    Statement statement = target.toStatement(textFixture);

    assertTrue(statement instanceof Credit);
    assertEquals(expectedCredit, statement);
  }

  private String createCreditStatementText() {
    return new StringBuilder(ItauStatementUtils.DATE_FIXTURE)
        .append("\t\t\t")
        .append(ItauStatementUtils.CREDIT_DETAIL_FIXTURE)
        .append("\t\t")
        .append(ItauStatementUtils.VALUE_FIXTURE)
        .append("\t\t\t")
        .toString();
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
