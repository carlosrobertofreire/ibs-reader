package com.github.carlosrvff.bsreader.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.carlosrvff.bsreader.domain.Balance;
import com.github.carlosrvff.bsreader.domain.Credit;
import com.github.carlosrvff.bsreader.domain.Debit;
import com.github.carlosrvff.bsreader.exception.InvalidStatementException;
import org.apache.commons.lang3.StringUtils;

class ItauConverterTestUtils {

  public static final String DATE_FIXTURE = "14/01/2019";
  public static final String VALUE_FIXTURE = "5000,00";
  public static final String DEBIT_DETAIL_FIXTURE = "RSHOP-STORE-14/01";
  public static final String BALANCE_DETAIL_FIXTURE = "RES APLIC AUT MAIS";
  public static final String CREDIT_DETAIL_FIXTURE = "REND PAGO APLIC AUT MAIS";

  static void toStatementWhenTextIsEmpty(ItauConverter target) {
    String textFixture = "";
    assertThrows(InvalidStatementException.class, () -> target.toStatement(textFixture));
  }

  static void toStatementWhenTextIsInvalid(ItauConverter target) {
    String textFixture = "InvalidText";
    assertThrows(InvalidStatementException.class, () -> target.toStatement(textFixture));
  }

  static void toStatementWhenTextIsNull(ItauConverter target) {
    assertThrows(NullPointerException.class, () -> target.toStatement(null));
  }

  static void toStatementWhenTextIsHeader(ItauConverter target) {
    String textFixture = target.getHeader();
    assertThrows(InvalidStatementException.class, () -> target.toStatement(textFixture));
  }

  static void getHeader(ItauConverter target) {
    assertTrue(StringUtils.isNotBlank(target.getHeader()));
  }

  static void toStatementWhenTextIsCredit(ItauConverter target, String textFixture) throws InvalidStatementException {
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

  static void toStatementWhenTextIsBalance(ItauConverter target, String textFixture) throws InvalidStatementException {
    Balance expectedBalance =
        Balance.builder().date(DATE_FIXTURE).value(VALUE_FIXTURE).originalText(textFixture).build();

    Balance balance = (Balance) target.toStatement(textFixture);

    assertEquals(expectedBalance, balance);
  }

  static void toStatementWhenTextIsDebit(ItauConverter target, String textFixture) throws InvalidStatementException {
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
}
