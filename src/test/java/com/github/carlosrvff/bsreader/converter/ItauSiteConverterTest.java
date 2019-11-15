package com.github.carlosrvff.bsreader.converter;

import com.github.carlosrvff.bsreader.exception.InvalidStatementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItauSiteConverterTest {

  private ItauSiteConverter target;

  @BeforeEach
  void setUp() {
    target = new ItauSiteConverter();
  }

  private String generateDebitStatementText() {
    return new StringBuilder(ItauConverterTestUtils.DATE_FIXTURE)
        .append("\t\t\t")
        .append(ItauConverterTestUtils.DEBIT_DETAIL_FIXTURE)
        .append("\t\t")
        .append(ItauConverterTestUtils.VALUE_FIXTURE)
        .append("-")
        .append("\t\t\t")
        .toString();
  }

  private String generateBalanceStatementText() {
    return new StringBuilder(ItauConverterTestUtils.DATE_FIXTURE)
        .append("\t\t\t")
        .append(ItauConverterTestUtils.BALANCE_DETAIL_FIXTURE)
        .append("\t\t\t\t")
        .append(ItauConverterTestUtils.VALUE_FIXTURE)
        .append("\t")
        .toString();
  }

  private String generateCreditStatementText() {
    return new StringBuilder(ItauConverterTestUtils.DATE_FIXTURE)
        .append("\t\t\t")
        .append(ItauConverterTestUtils.CREDIT_DETAIL_FIXTURE)
        .append("\t\t")
        .append(ItauConverterTestUtils.VALUE_FIXTURE)
        .append("\t\t\t")
        .toString();
  }

  @Test
  void toStatementWhenTextIsDebit() throws InvalidStatementException {
    ItauConverterTestUtils.toStatementWhenTextIsDebit(target, generateDebitStatementText());
  }

  @Test
  void toStatementWhenTextIsBalance() throws InvalidStatementException {
    ItauConverterTestUtils.toStatementWhenTextIsBalance(target, generateBalanceStatementText());
  }

  @Test
  void toStatementWhenTextIsCredit() throws InvalidStatementException {
    ItauConverterTestUtils.toStatementWhenTextIsCredit(target, generateCreditStatementText());
  }

  @Test
  void toStatementWhenTextIsEmpty() {
    ItauConverterTestUtils.toStatementWhenTextIsEmpty(target);
  }

  @Test
  void toStatementWhenTextIsInvalid() {
    ItauConverterTestUtils.toStatementWhenTextIsInvalid(target);
  }

  @Test
  void toStatementWhenTextIsNull() {
    ItauConverterTestUtils.toStatementWhenTextIsNull(target);
  }

  @Test
  void toStatementWhenTextIsHeader() {
    ItauConverterTestUtils.toStatementWhenTextIsHeader(target);
  }

  @Test
  void getHeader() {
    ItauConverterTestUtils.getHeader(target);
  }
}
