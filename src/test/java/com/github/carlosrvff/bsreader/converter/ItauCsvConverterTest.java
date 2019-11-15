package com.github.carlosrvff.bsreader.converter;

import static org.junit.jupiter.api.Assertions.*;

import com.github.carlosrvff.bsreader.domain.Balance;
import com.github.carlosrvff.bsreader.domain.Credit;
import com.github.carlosrvff.bsreader.domain.Debit;
import com.github.carlosrvff.bsreader.exception.InvalidStatementException;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItauCsvConverterTest {

  public static final String DATE_FIXTURE = "14/01/2019";
  public static final String VALUE_FIXTURE = "5000,00";
  public static final String DEBIT_DETAIL_FIXTURE = "RSHOP-STORE-14/01";
  public static final String BALANCE_DETAIL_FIXTURE = "RES APLIC AUT MAIS";
  public static final String CREDIT_DETAIL_FIXTURE = "REND PAGO APLIC AUT MAIS";
  public static final String SEPARATOR = ";";

  private ItauCsvConverter target;

  @BeforeEach
  void setUp() {
    target = new ItauCsvConverter();
  }

  @Test
  void toStatementWhenTextIsDebit() throws InvalidStatementException {
    String textFixture = generateDebitStatementText();
    Debit expectedDebit =
        Debit.builder()
            .date(DATE_FIXTURE)
            .store(DEBIT_DETAIL_FIXTURE)
            .value(VALUE_FIXTURE)
            .originalText(textFixture)
            .build();

    Debit debit = (Debit) target.toStatement(textFixture);

    assertEquals(expectedDebit, debit);
  }

  private String generateDebitStatementText() {
    return new StringBuilder(DATE_FIXTURE)
        .append(SEPARATOR)
        .append(DEBIT_DETAIL_FIXTURE)
        .append(SEPARATOR)
        .append(target.DEBIT_SYMBOL)
        .append(VALUE_FIXTURE)
        .toString();
  }

  @Test
  void toStatementWhenTextIsBalance() throws InvalidStatementException {
    String textFixture = generateBalanceStatementText();
    Balance expectedBalance =
        Balance.builder().date(DATE_FIXTURE).value(VALUE_FIXTURE).originalText(textFixture).build();

    Balance balance = (Balance) target.toStatement(textFixture);

    assertEquals(expectedBalance, balance);
  }

  private String generateBalanceStatementText() {
    return new StringBuilder(DATE_FIXTURE)
        .append(SEPARATOR)
        .append(BALANCE_DETAIL_FIXTURE)
        .append(SEPARATOR)
        .append(VALUE_FIXTURE)
        .toString();
  }

  @Test
  void toStatementWhenTextIsCredit() throws InvalidStatementException {
    String textFixture = generateCreditStatementText();

    Credit expectedCredit =
        Credit.builder()
            .date(DATE_FIXTURE)
            .from(CREDIT_DETAIL_FIXTURE)
            .value(VALUE_FIXTURE)
            .originalText(textFixture)
            .build();

    Credit credit = (Credit) target.toStatement(textFixture);

    assertEquals(expectedCredit, credit);
  }

  private String generateCreditStatementText() {
    return new StringBuilder(DATE_FIXTURE)
        .append(SEPARATOR)
        .append(CREDIT_DETAIL_FIXTURE)
        .append(SEPARATOR)
        .append(VALUE_FIXTURE)
        .toString();
  }

  @Test
  void toStatementWhenTextIsEmpty() {
    String textFixture = "";
    assertThrows(InvalidStatementException.class, () -> target.toStatement(textFixture));
  }

  @Test
  void toStatementWhenTextIsInvalid() {
    String textFixture = "03/01;Hello";
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
