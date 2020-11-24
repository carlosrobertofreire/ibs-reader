package com.carlosrvff.bsreader.helper;

import com.carlosrvff.bsreader.domain.statement.Balance;
import com.carlosrvff.bsreader.domain.statement.Credit;
import com.carlosrvff.bsreader.domain.statement.Debit;

public class ItauStatementUtils {

  public static final String DATE_FIXTURE = "14/01/2019";
  public static final String VALUE_FIXTURE = "5000,00";
  public static final String DEBIT_DETAIL_FIXTURE = "RSHOP-STORE-14/01";
  public static final String BALANCE_DETAIL_FIXTURE = "RES APLIC AUT MAIS";
  public static final String CREDIT_DETAIL_FIXTURE = "REND PAGO APLIC AUT MAIS";

  public Credit createCreditStatement(String originalText) {
    return Credit.builder()
        .date(DATE_FIXTURE)
        .from(CREDIT_DETAIL_FIXTURE)
        .value(VALUE_FIXTURE)
        .originalText(originalText)
        .build();
  }

  public Debit createDebitStatement(String originalText) {
    return Debit.builder()
        .date(DATE_FIXTURE)
        .store(DEBIT_DETAIL_FIXTURE)
        .value(VALUE_FIXTURE)
        .originalText(originalText)
        .build();
  }

  public Balance createBalanceStatement(String originalText) {
    return Balance.builder()
        .date(DATE_FIXTURE)
        .value(VALUE_FIXTURE)
        .originalText(originalText)
        .build();
  }
}
