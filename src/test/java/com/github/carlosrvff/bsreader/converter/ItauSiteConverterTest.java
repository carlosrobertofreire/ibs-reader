package com.github.carlosrvff.bsreader.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.carlosrvff.bsreader.builder.ItauStatementUtils;
import com.github.carlosrvff.bsreader.domain.Balance;
import com.github.carlosrvff.bsreader.domain.Credit;
import com.github.carlosrvff.bsreader.domain.Debit;
import com.github.carlosrvff.bsreader.exception.InvalidStatementException;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItauSiteConverterTest {

  private ItauSiteConverter target;

  @BeforeEach
  void setUp() {
    target = new ItauSiteConverter();
  }

  private String generateDebitStatementText() {
    return new StringBuilder(ItauStatementUtils.DATE_FIXTURE)
        .append("\t\t\t")
        .append(ItauStatementUtils.DEBIT_DETAIL_FIXTURE)
        .append("\t\t")
        .append(ItauStatementUtils.VALUE_FIXTURE)
        .append("-")
        .append("\t\t\t")
        .toString();
  }

  private String generateBalanceStatementText() {
    return new StringBuilder(ItauStatementUtils.DATE_FIXTURE)
        .append("\t\t\t")
        .append(ItauStatementUtils.BALANCE_DETAIL_FIXTURE)
        .append("\t\t\t\t")
        .append(ItauStatementUtils.VALUE_FIXTURE)
        .append("\t")
        .toString();
  }

  private String generateCreditStatementText() {
    return new StringBuilder(ItauStatementUtils.DATE_FIXTURE)
        .append("\t\t\t")
        .append(ItauStatementUtils.CREDIT_DETAIL_FIXTURE)
        .append("\t\t")
        .append(ItauStatementUtils.VALUE_FIXTURE)
        .append("\t\t\t")
        .toString();
  }

  @Test
  void toStatementWhenTextIsDebit() throws InvalidStatementException {
    String textFixture = generateDebitStatementText();

    Debit expectedDebit = ItauStatementUtils.generateDebitStatement(textFixture);

    Debit debit = (Debit) target.toStatement(textFixture);

    assertEquals(expectedDebit, debit);
  }

  @Test
  void toStatementWhenTextIsBalance() throws InvalidStatementException {
    String textFixture = generateBalanceStatementText();

    Balance expectedBalance = ItauStatementUtils.generateBalanceStatement(textFixture);

    Balance balance = (Balance) target.toStatement(textFixture);

    assertEquals(expectedBalance, balance);
  }

  @Test
  void toStatementWhenTextIsCredit() throws InvalidStatementException {
    String textFixture = generateCreditStatementText();

    Credit expectedCredit = ItauStatementUtils.generateCreditStatement(textFixture);

    Credit credit = (Credit) target.toStatement(textFixture);

    assertEquals(expectedCredit, credit);
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
