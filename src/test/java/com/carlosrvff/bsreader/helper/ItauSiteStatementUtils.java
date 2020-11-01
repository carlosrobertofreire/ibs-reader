package com.carlosrvff.bsreader.helper;

public class ItauSiteStatementUtils extends ItauStatementUtils {

  public static String createDebitStatementText() {
    return new StringBuilder(ItauStatementUtils.DATE_FIXTURE)
        .append("\t\t\t")
        .append(ItauStatementUtils.DEBIT_DETAIL_FIXTURE)
        .append("\t\t")
        .append(ItauStatementUtils.VALUE_FIXTURE)
        .append("-")
        .append("\t\t\t")
        .toString();
  }

  public static String createBalanceStatementText() {
    return new StringBuilder(ItauStatementUtils.DATE_FIXTURE)
        .append("\t\t\t")
        .append(ItauStatementUtils.BALANCE_DETAIL_FIXTURE)
        .append("\t\t\t\t")
        .append(ItauStatementUtils.VALUE_FIXTURE)
        .append("\t")
        .toString();
  }

  public static String createCreditStatementText() {
    return new StringBuilder(ItauStatementUtils.DATE_FIXTURE)
        .append("\t\t\t")
        .append(ItauStatementUtils.CREDIT_DETAIL_FIXTURE)
        .append("\t\t")
        .append(ItauStatementUtils.VALUE_FIXTURE)
        .append("\t\t\t")
        .toString();
  }
}
