package com.carlosrvff.bsreader.helper;

import com.carlosrvff.bsreader.converter.CheetahConverter;
import com.carlosrvff.bsreader.domain.statement.Credit;
import com.carlosrvff.bsreader.domain.statement.Debit;

public class CheetahStatementUtils implements BankStatementUtils {

  public static final String SEPARATOR = ",";
  private static final String DATE_FIXTURE = "24/06/2019   17:36";
  private static final String TRANSACTION_TYPE_FIXTURE = "POS Domestic";
  private static final String MERCHANT_FIXTURE = "ANY STORE";
  private static final String FIVE_THOUSAND_AMOUNT_FIXTURE = "5000.00";
  private static final String ZERO_AMOUNT_FIXTURE = "0.00";
  private static final String FEE_FIXTURE = "0.20";
  private static final String RESULT_FIXTURE = "APPROVED";

  public String createFeeDebitStatementText() {
    return new StringBuilder(DATE_FIXTURE)
        .append(SEPARATOR)
        .append(TRANSACTION_TYPE_FIXTURE)
        .append(SEPARATOR)
        .append(SEPARATOR)
        .append(CheetahConverter.CURRENCY_SYMBOL)
        .append(" ")
        .append(ZERO_AMOUNT_FIXTURE)
        .append(SEPARATOR)
        .append(CheetahConverter.FEE_MARKER_FIXTURE)
        .append(CheetahConverter.DEBIT_SYMBOL)
        .append(" ")
        .append(CheetahConverter.CURRENCY_SYMBOL)
        .append(" ")
        .append(FEE_FIXTURE)
        .append(CheetahConverter.FEE_MARKER_FIXTURE)
        .append(SEPARATOR)
        .append(RESULT_FIXTURE)
        .toString();
  }

  public Debit createFeeDebit(String textFixture) {
    return Debit.builder()
        .originalText(textFixture)
        .value(
            new StringBuilder(ZERO_AMOUNT_FIXTURE)
                .append(CheetahConverter.ADD_SYMBOL)
                .append(FEE_FIXTURE)
                .toString())
        .date(DATE_FIXTURE)
        .build();
  }

  public String createDebitStatementText() {
    return new StringBuilder(DATE_FIXTURE)
        .append(SEPARATOR)
        .append(TRANSACTION_TYPE_FIXTURE)
        .append(SEPARATOR)
        .append(MERCHANT_FIXTURE)
        .append(SEPARATOR)
        .append(CheetahConverter.DEBIT_SYMBOL)
        .append(" ")
        .append(CheetahConverter.CURRENCY_SYMBOL)
        .append(" ")
        .append(FIVE_THOUSAND_AMOUNT_FIXTURE)
        .append(SEPARATOR)
        .append(CheetahConverter.FEE_MARKER_FIXTURE)
        .append(CheetahConverter.DEBIT_SYMBOL)
        .append(" ")
        .append(CheetahConverter.CURRENCY_SYMBOL)
        .append(" ")
        .append(FEE_FIXTURE)
        .append(CheetahConverter.FEE_MARKER_FIXTURE)
        .append(SEPARATOR)
        .append(RESULT_FIXTURE)
        .toString();
  }

  public Debit createDebitStatement(String textFixture) {
    return Debit.builder()
        .originalText(textFixture)
        .value(
            new StringBuilder(FIVE_THOUSAND_AMOUNT_FIXTURE)
                .append(CheetahConverter.ADD_SYMBOL)
                .append(FEE_FIXTURE)
                .toString())
        .store(MERCHANT_FIXTURE)
        .date(DATE_FIXTURE)
        .build();
  }

  public String createCreditStatementText() {
    return new StringBuilder(DATE_FIXTURE)
        .append(SEPARATOR)
        .append(TRANSACTION_TYPE_FIXTURE)
        .append(SEPARATOR)
        .append(MERCHANT_FIXTURE)
        .append(SEPARATOR)
        .append(CheetahConverter.CURRENCY_SYMBOL)
        .append(" ")
        .append(FIVE_THOUSAND_AMOUNT_FIXTURE)
        .append(SEPARATOR)
        .append(CheetahConverter.FEE_MARKER_FIXTURE)
        .append(CheetahConverter.FEE_MARKER_FIXTURE)
        .append(SEPARATOR)
        .append(RESULT_FIXTURE)
        .toString();
  }

  public Credit createCreditStatement(String textFixture) {
    return Credit.builder()
        .originalText(textFixture)
        .from(MERCHANT_FIXTURE)
        .date(DATE_FIXTURE)
        .value(FIVE_THOUSAND_AMOUNT_FIXTURE)
        .build();
  }

}
