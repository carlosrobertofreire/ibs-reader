package com.carlosrvff.bsreader.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.carlosrvff.bsreader.domain.Credit;
import com.carlosrvff.bsreader.domain.Debit;
import com.carlosrvff.bsreader.domain.Statement;
import com.carlosrvff.bsreader.exception.InvalidStatementException;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CheetahConverterTest {

  private static final String DATE_FIXTURE = "24/06/2019   17:36";
  private static final String TRANSACTION_TYPE_FIXTURE = "POS Domestic";
  private static final String MERCHANT_FIXTURE = "ANY STORE";
  private static final String FIVE_THOUSAND_AMOUNT_FIXTURE = "5000.00";
  private static final String ZERO_AMOUNT_FIXTURE = "0.00";
  private static final String FEE_FIXTURE = "0.20";
  private static final String RESULT_FIXTURE = "APPROVED";

  private CheetahConverter target;

  @BeforeEach
  void setUp() {
    target = new CheetahConverter();
  }

  @Test
  void toStatementWhenTextIsFeeDebit() throws InvalidStatementException {
    String textFixture = createFeeDebitStatementText();
    Debit expectedFeeDebit = createFeeDebit(textFixture);

    Statement statement = target.toStatement(textFixture);

    assertTrue(statement instanceof Debit);
    assertEquals(expectedFeeDebit, statement);
  }

  private String createFeeDebitStatementText() {
    return new StringBuilder(DATE_FIXTURE)
        .append(target.SEPARATOR)
        .append(TRANSACTION_TYPE_FIXTURE)
        .append(target.SEPARATOR)
        .append(target.SEPARATOR)
        .append(target.CURRENCY_SYMBOL)
        .append(" ")
        .append(ZERO_AMOUNT_FIXTURE)
        .append(target.SEPARATOR)
        .append(target.FEE_MARKER_FIXTURE)
        .append(target.DEBIT_SYMBOL)
        .append(" ")
        .append(target.CURRENCY_SYMBOL)
        .append(" ")
        .append(FEE_FIXTURE)
        .append(target.FEE_MARKER_FIXTURE)
        .append(target.SEPARATOR)
        .append(RESULT_FIXTURE)
        .toString();
  }

  private Debit createFeeDebit(String textFixture) {
    return Debit.builder()
        .originalText(textFixture)
        .value(
            new StringBuilder(ZERO_AMOUNT_FIXTURE)
                .append(target.ADD_SYMBOL)
                .append(FEE_FIXTURE)
                .toString())
        .date(DATE_FIXTURE)
        .build();
  }

  @Test
  void toStatementWhenTextIsPurchaseDebit() throws InvalidStatementException {
    String textFixture = createPurchaseDebitStatementText();
    Debit expectedPurchaseDebit = createPurchaseDebit(textFixture);

    Statement statement = target.toStatement(textFixture);

    assertTrue(statement instanceof Debit);
    assertEquals(expectedPurchaseDebit, statement);
  }

  private String createPurchaseDebitStatementText() {
    return new StringBuilder(DATE_FIXTURE)
        .append(target.SEPARATOR)
        .append(TRANSACTION_TYPE_FIXTURE)
        .append(target.SEPARATOR)
        .append(MERCHANT_FIXTURE)
        .append(target.SEPARATOR)
        .append(target.DEBIT_SYMBOL)
        .append(" ")
        .append(target.CURRENCY_SYMBOL)
        .append(" ")
        .append(FIVE_THOUSAND_AMOUNT_FIXTURE)
        .append(target.SEPARATOR)
        .append(target.FEE_MARKER_FIXTURE)
        .append(target.DEBIT_SYMBOL)
        .append(" ")
        .append(target.CURRENCY_SYMBOL)
        .append(" ")
        .append(FEE_FIXTURE)
        .append(target.FEE_MARKER_FIXTURE)
        .append(target.SEPARATOR)
        .append(RESULT_FIXTURE)
        .toString();
  }

  private Debit createPurchaseDebit(String textFixture) {
    return Debit.builder()
        .originalText(textFixture)
        .value(
            new StringBuilder(FIVE_THOUSAND_AMOUNT_FIXTURE)
                .append(target.ADD_SYMBOL)
                .append(FEE_FIXTURE)
                .toString())
        .store(MERCHANT_FIXTURE)
        .date(DATE_FIXTURE)
        .build();
  }

  @Test
  void toStatementWhenTextIsCredit() throws InvalidStatementException {
    String textFixture = createCreditStatementText();
    Credit expectedCredit = createCredit(textFixture);

    Statement statement = target.toStatement(textFixture);

    assertTrue(statement instanceof Credit);
    assertEquals(expectedCredit, statement);
  }

  private String createCreditStatementText() {
    return new StringBuilder(DATE_FIXTURE)
        .append(target.SEPARATOR)
        .append(TRANSACTION_TYPE_FIXTURE)
        .append(target.SEPARATOR)
        .append(MERCHANT_FIXTURE)
        .append(target.SEPARATOR)
        .append(target.CURRENCY_SYMBOL)
        .append(" ")
        .append(FIVE_THOUSAND_AMOUNT_FIXTURE)
        .append(target.SEPARATOR)
        .append(target.FEE_MARKER_FIXTURE)
        .append(target.FEE_MARKER_FIXTURE)
        .append(target.SEPARATOR)
        .append(RESULT_FIXTURE)
        .toString();
  }

  private Credit createCredit(String textFixture) {
    return Credit.builder()
        .originalText(textFixture)
        .from(MERCHANT_FIXTURE)
        .date(DATE_FIXTURE)
        .value(FIVE_THOUSAND_AMOUNT_FIXTURE)
        .build();
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
