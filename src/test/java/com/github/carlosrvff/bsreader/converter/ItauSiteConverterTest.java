package com.github.carlosrvff.bsreader.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.carlosrvff.bsreader.domain.Balance;
import com.github.carlosrvff.bsreader.domain.Credit;
import com.github.carlosrvff.bsreader.domain.Debit;
import com.github.carlosrvff.bsreader.exception.InvalidStatementException;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItauSiteConverterTest {

  public static final String DATE_FIXTURE = "01/12";
  public static final String VALUE_FIXTURE = "1.000,00";
  public static final String DETAIL_FIXTURE = "Detail";
  public static final String EXTRA_DETAIL_FIXTURE = "Extra Detail";

  private ItauSiteConverter target;

  @BeforeEach
  void setUp() {
    target = new ItauSiteConverter();
  }

  @Test
  void toStatementWhenTextIsDebit() throws InvalidStatementException {
    String textFixture = generateDebitStatementText();
    Debit expectedDebit =
        Debit.builder()
            .date(DATE_FIXTURE)
            .store(DETAIL_FIXTURE)
            .value(VALUE_FIXTURE)
            .originalText(textFixture)
            .build();

    Debit debit = (Debit) target.toStatement(textFixture);
    assertEquals(expectedDebit, debit);
  }

  private String generateDebitStatementText() {
    return new StringBuilder(DATE_FIXTURE)
        .append("\t\t\t")
        .append(DETAIL_FIXTURE)
        .append("\t\t")
        .append(VALUE_FIXTURE)
        .append("-")
        .append("\t\t\t")
        .toString();
  }

  @Test
  void toStatementWhenTextIsCredit() throws InvalidStatementException {
    String textFixture = generateCreditStatementText();

    Credit expectedCredit =
        Credit.builder()
            .date(DATE_FIXTURE)
            .from(DETAIL_FIXTURE)
            .value(VALUE_FIXTURE)
            .originalText(textFixture)
            .build();

    Credit credit = (Credit) target.toStatement(textFixture);

    assertEquals(expectedCredit, credit);
  }

  private String generateCreditStatementText() {
    return new StringBuilder(DATE_FIXTURE)
        .append("\t\t\t")
        .append(DETAIL_FIXTURE)
        .append("\t\t")
        .append(VALUE_FIXTURE)
        .append("\t\t\t")
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
        .append("\t\t\t")
        .append(EXTRA_DETAIL_FIXTURE)
        .append("\t\t\t\t")
        .append(VALUE_FIXTURE)
        .append("\t")
        .toString();
  }

  @Test
  void toStatementWhenTextIsInvalid() {
    String textFixture = "adsadas\t";
    assertThrows(InvalidStatementException.class, () -> target.toStatement(textFixture));
  }

  @Test
  void toStatementWhenTextIsEmpty() {
    String textFixture = "";
    assertThrows(InvalidStatementException.class, () -> target.toStatement(textFixture));
  }

  @Test
  void toStatementWhenTextIsIsNull() {
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
