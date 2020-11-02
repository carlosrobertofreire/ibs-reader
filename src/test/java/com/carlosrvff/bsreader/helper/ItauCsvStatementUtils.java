package com.carlosrvff.bsreader.helper;

import com.carlosrvff.bsreader.converter.BankConverter;

public class ItauCsvStatementUtils extends ItauStatementUtils implements BankStatementUtils {

  public static final String SEPARATOR = ";";

  public String createDebitStatementText() {
    return new StringBuilder(ItauStatementUtils.DATE_FIXTURE)
        .append(SEPARATOR)
        .append(ItauStatementUtils.DEBIT_DETAIL_FIXTURE)
        .append(SEPARATOR)
        .append(BankConverter.DEBIT_SYMBOL)
        .append(ItauStatementUtils.VALUE_FIXTURE)
        .toString();
  }

  public String createCreditStatementText() {
    return new StringBuilder(ItauStatementUtils.DATE_FIXTURE)
        .append(SEPARATOR)
        .append(ItauStatementUtils.CREDIT_DETAIL_FIXTURE)
        .append(SEPARATOR)
        .append(ItauStatementUtils.VALUE_FIXTURE)
        .toString();
  }

  public static String createBalanceStatementText() {
    return new StringBuilder(ItauStatementUtils.DATE_FIXTURE)
        .append(SEPARATOR)
        .append(ItauStatementUtils.BALANCE_DETAIL_FIXTURE)
        .append(SEPARATOR)
        .append(ItauStatementUtils.VALUE_FIXTURE)
        .toString();
  }
}
