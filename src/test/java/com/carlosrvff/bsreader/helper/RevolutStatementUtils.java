package com.carlosrvff.bsreader.helper;

import com.carlosrvff.bsreader.domain.statement.Credit;
import com.carlosrvff.bsreader.domain.statement.Debit;

public class RevolutStatementUtils implements BankStatementUtils {

  private static final String SEPARATOR = ";";

  private static final String COMPLETED_DATE = "18 Nov 2020";
  private static final String REFERENCE = "ANY REFERENCE";
  private static final String PAID_OUT_EUR = "35.90";
  private static final String PAID_IN_EUR = "1,500.00";
  private static final String EXCHANGE_OUT = "GBP 40.20";
  private static final String EXCHANGE_IN = "GBP 60.50";
  private static final String BALANCE_EUR = "478.52";
  private static final String EXCHANGE_RATE = "FX-rate €1 = £0.8875";
  private static final String CATEGORY = "Shopping";

  @Override
  public String createDebitStatementText() {
    return new StringBuilder()
        .append(COMPLETED_DATE)
        .append(SEPARATOR)
        .append(REFERENCE)
        .append(SEPARATOR)
        .append(PAID_OUT_EUR)
        .append(SEPARATOR)
        .append(SEPARATOR)
        .append(SEPARATOR)
        .append(SEPARATOR)
        .append(BALANCE_EUR)
        .append(SEPARATOR)
        .append(SEPARATOR)
        .append(CATEGORY)
        .toString();
  }

  @Override
  public String createCreditStatementText() {
    return new StringBuilder()
        .append(COMPLETED_DATE)
        .append(SEPARATOR)
        .append(REFERENCE)
        .append(SEPARATOR)
        .append(SEPARATOR)
        .append(PAID_IN_EUR)
        .append(SEPARATOR)
        .append(SEPARATOR)
        .append(SEPARATOR)
        .append(BALANCE_EUR)
        .append(SEPARATOR)
        .append(SEPARATOR)
        .append(CATEGORY)
        .toString();
  }

  @Override
  public Debit createDebitStatement(String originalText) {
    return Debit.builder()
        .originalText(originalText)
        .value(PAID_OUT_EUR)
        .store(REFERENCE)
        .date(COMPLETED_DATE)
        .category(CATEGORY)
        .build();
  }

  @Override
  public Credit createCreditStatement(String originalText) {
    return Credit.builder()
        .originalText(originalText)
        .from(REFERENCE)
        .date(COMPLETED_DATE)
        .value(PAID_IN_EUR)
        .build();
  }
}
