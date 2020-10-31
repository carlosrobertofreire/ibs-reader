package com.carlosrvff.bsreader.builder;

import com.carlosrvff.bsreader.domain.Balance;
import com.carlosrvff.bsreader.domain.Credit;
import com.carlosrvff.bsreader.domain.Debit;

public class ItauStatementUtils {

  public static final String DATE_FIXTURE = "14/01/2019";
  public static final String VALUE_FIXTURE = "5000,00";
  public static final String DEBIT_DETAIL_FIXTURE = "RSHOP-STORE-14/01";
  public static final String BALANCE_DETAIL_FIXTURE = "RES APLIC AUT MAIS";
  public static final String CREDIT_DETAIL_FIXTURE = "REND PAGO APLIC AUT MAIS";

  public static Credit generateCreditStatement(String originalText) {
    return Credit.builder()
        .date(DATE_FIXTURE)
        .from(CREDIT_DETAIL_FIXTURE)
        .value(VALUE_FIXTURE)
        .originalText(originalText)
        .build();
  }

  public static Debit generateDebitStatement(String originalText) {
    return Debit.builder()
        .date(DATE_FIXTURE)
        .store(DEBIT_DETAIL_FIXTURE)
        .value(VALUE_FIXTURE)
        .originalText(originalText)
        .build();
  }

  public static Balance generateBalanceStatement(String originalText) {
    return Balance.builder()
        .date(DATE_FIXTURE)
        .value(VALUE_FIXTURE)
        .originalText(originalText)
        .build();
  }
}
