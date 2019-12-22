package com.github.carlosrvff.bsreader.converter;

import static org.junit.jupiter.api.Assertions.*;

import com.github.carlosrvff.bsreader.domain.Credit;
import com.github.carlosrvff.bsreader.domain.Debit;
import com.github.carlosrvff.bsreader.exception.InvalidStatementException;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KbcConverterTest {

  private static final String DATE_FIXTURE = "30 Jul 2019";
  private static final String TRANSACTION_FIXTURE = "ANY STORE";
  private static final String FIVE_THOUSAND_AMOUNT_FIXTURE = "5,000.00";

  private KbcConverter target;

  @BeforeEach
  void setUp() {
    target = new KbcConverter();
  }

  private String generateDebitStatementText() {
    return new StringBuilder(DATE_FIXTURE)
        .append(target.SEPARATOR)
        .append(target.SEPARATOR)
        .append(TRANSACTION_FIXTURE)
        .append(target.SEPARATOR)
        .append(target.DEBIT_SYMBOL)
        .append(target.CURRENCY_SYMBOL)
        .append(FIVE_THOUSAND_AMOUNT_FIXTURE)
        .append(target.SEPARATOR)
        .append(target.CURRENCY_SYMBOL)
        .append(FIVE_THOUSAND_AMOUNT_FIXTURE)
        .toString();
  }

  private String generateCreditStatementText() {
    return new StringBuilder(DATE_FIXTURE)
        .append(target.SEPARATOR)
        .append(target.SEPARATOR)
        .append(TRANSACTION_FIXTURE)
        .append(target.SEPARATOR)
        .append(target.CURRENCY_SYMBOL)
        .append(FIVE_THOUSAND_AMOUNT_FIXTURE)
        .append(target.SEPARATOR)
        .append(target.SEPARATOR)
        .append(target.CURRENCY_SYMBOL)
        .append(FIVE_THOUSAND_AMOUNT_FIXTURE)
        .toString();
  }

  @Test
  void toStatementWhenTextIsDebit() throws InvalidStatementException {
    String textFixture = generateDebitStatementText();

    Debit expectedDebit =
        Debit.builder()
            .originalText(textFixture)
            .value(FIVE_THOUSAND_AMOUNT_FIXTURE)
            .store(TRANSACTION_FIXTURE)
            .date(DATE_FIXTURE)
            .build();

    Debit debit = (Debit) target.toStatement(textFixture);

    assertEquals(expectedDebit, debit);
  }

  @Test
  void toStatementWhenTextIsCredit() throws InvalidStatementException {
    String textFixture = generateCreditStatementText();

    Credit expectedCredit =
        Credit.builder()
            .originalText(textFixture)
            .from(TRANSACTION_FIXTURE)
            .date(DATE_FIXTURE)
            .value(FIVE_THOUSAND_AMOUNT_FIXTURE)
            .build();

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