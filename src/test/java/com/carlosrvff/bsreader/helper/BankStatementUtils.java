package com.carlosrvff.bsreader.helper;

import com.carlosrvff.bsreader.domain.Credit;
import com.carlosrvff.bsreader.domain.Debit;

public interface BankStatementUtils {

  String createDebitStatementText();

  String createCreditStatementText();

  Debit createDebitStatement(String originalText);

  Credit createCreditStatement(String originalText);
}
