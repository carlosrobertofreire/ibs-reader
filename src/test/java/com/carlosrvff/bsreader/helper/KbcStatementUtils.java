package com.carlosrvff.bsreader.helper;

import com.carlosrvff.bsreader.converter.KbcConverter;
import com.carlosrvff.bsreader.domain.Credit;
import com.carlosrvff.bsreader.domain.Debit;

public class KbcStatementUtils {

  private static final String DATE_FIXTURE = "30 Jul 2019";
  private static final String TRANSACTION_FIXTURE = "ANY STORE";
  private static final String FIVE_THOUSAND_AMOUNT_FIXTURE = "5,000.00";

  public static String createProcessedDebitStatementText() {
    return new StringBuilder(DATE_FIXTURE)
        .append(KbcConverter.SEPARATOR)
        .append(KbcConverter.SEPARATOR)
        .append(TRANSACTION_FIXTURE)
        .append(KbcConverter.SEPARATOR)
        .append(KbcConverter.DEBIT_SYMBOL)
        .append(KbcConverter.CURRENCY_SYMBOL)
        .append(FIVE_THOUSAND_AMOUNT_FIXTURE)
        .append(KbcConverter.SEPARATOR)
        .append(KbcConverter.CURRENCY_SYMBOL)
        .append(FIVE_THOUSAND_AMOUNT_FIXTURE)
        .toString();
  }

  public static Debit createExpectedDebit(String textFixture) {
    return Debit.builder()
        .originalText(textFixture)
        .value(FIVE_THOUSAND_AMOUNT_FIXTURE)
        .store(TRANSACTION_FIXTURE)
        .date(DATE_FIXTURE)
        .build();
  }

  public static String createProcessedCreditStatementText() {
    return new StringBuilder(DATE_FIXTURE)
        .append(KbcConverter.SEPARATOR)
        .append(KbcConverter.SEPARATOR)
        .append(TRANSACTION_FIXTURE)
        .append(KbcConverter.SEPARATOR)
        .append(KbcConverter.CURRENCY_SYMBOL)
        .append(FIVE_THOUSAND_AMOUNT_FIXTURE)
        .append(KbcConverter.SEPARATOR)
        .append(KbcConverter.SEPARATOR)
        .append(KbcConverter.CURRENCY_SYMBOL)
        .append(FIVE_THOUSAND_AMOUNT_FIXTURE)
        .toString();
  }

  public static Credit createExpectedCredit(String textFixture) {
    return Credit.builder()
        .originalText(textFixture)
        .from(TRANSACTION_FIXTURE)
        .date(DATE_FIXTURE)
        .value(FIVE_THOUSAND_AMOUNT_FIXTURE)
        .build();
  }

  public static String createOriginalDebitStatementText() {
    return new StringBuilder(DATE_FIXTURE)
        .append(KbcConverter.SEPARATOR)
        .append(KbcConverter.SEPARATOR)
        .append(System.lineSeparator())
        .append(TRANSACTION_FIXTURE)
        .append(KbcConverter.SEPARATOR)
        .append(System.lineSeparator())
        .append(KbcConverter.DEBIT_SYMBOL)
        .append(KbcConverter.CURRENCY_SYMBOL)
        .append(FIVE_THOUSAND_AMOUNT_FIXTURE)
        .append(KbcConverter.SEPARATOR)
        .append(KbcConverter.CURRENCY_SYMBOL)
        .append(FIVE_THOUSAND_AMOUNT_FIXTURE)
        .append(System.lineSeparator())
        .toString();
  }

  public static String createOriginalCreditStatementText() {
    return new StringBuilder(DATE_FIXTURE)
        .append(KbcConverter.SEPARATOR)
        .append(KbcConverter.SEPARATOR)
        .append(System.lineSeparator())
        .append(TRANSACTION_FIXTURE)
        .append(KbcConverter.SEPARATOR)
        .append(System.lineSeparator())
        .append(KbcConverter.CURRENCY_SYMBOL)
        .append(FIVE_THOUSAND_AMOUNT_FIXTURE)
        .append(KbcConverter.SEPARATOR)
        .append(KbcConverter.SEPARATOR)
        .append(KbcConverter.CURRENCY_SYMBOL)
        .append(FIVE_THOUSAND_AMOUNT_FIXTURE)
        .append(System.lineSeparator())
        .toString();
  }

  public static String createTwoStatementsText() {
    String debitText = KbcStatementUtils.createOriginalDebitStatementText();
    String creditText = KbcStatementUtils.createOriginalCreditStatementText();
    return mergeStrings(debitText, creditText);
  }

  private static String mergeStrings(String... texts) {
    StringBuilder result = new StringBuilder();
    for (String text : texts) {
      result.append(text);
    }
    return result.toString();
  }

}
