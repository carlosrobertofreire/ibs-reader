package com.github.carlosrvff.bsreader.converter;

import com.github.carlosrvff.bsreader.exception.InvalidStatementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItauCsvConverterTest {

  public static final String SEPARATOR = ";";

  private ItauCsvConverter target;

  @BeforeEach
  void setUp() {
    target = new ItauCsvConverter();
  }

  private String generateDebitStatementText() {
    return new StringBuilder(ItauConverterTestUtils.DATE_FIXTURE)
        .append(SEPARATOR)
        .append(ItauConverterTestUtils.DEBIT_DETAIL_FIXTURE)
        .append(SEPARATOR)
        .append(target.DEBIT_SYMBOL)
        .append(ItauConverterTestUtils.VALUE_FIXTURE)
        .toString();
  }

  private String generateBalanceStatementText() {
    return new StringBuilder(ItauConverterTestUtils.DATE_FIXTURE)
        .append(SEPARATOR)
        .append(ItauConverterTestUtils.BALANCE_DETAIL_FIXTURE)
        .append(SEPARATOR)
        .append(ItauConverterTestUtils.VALUE_FIXTURE)
        .toString();
  }

  private String generateCreditStatementText() {
    return new StringBuilder(ItauConverterTestUtils.DATE_FIXTURE)
        .append(SEPARATOR)
        .append(ItauConverterTestUtils.CREDIT_DETAIL_FIXTURE)
        .append(SEPARATOR)
        .append(ItauConverterTestUtils.VALUE_FIXTURE)
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
