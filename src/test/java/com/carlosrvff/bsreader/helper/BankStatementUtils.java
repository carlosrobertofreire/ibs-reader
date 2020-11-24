package com.carlosrvff.bsreader.helper;

import com.carlosrvff.bsreader.domain.statement.Credit;
import com.carlosrvff.bsreader.domain.statement.Debit;

public interface BankStatementUtils {

  String createDebitStatementText();

  String createCreditStatementText();

  Debit createDebitStatement(String originalText);

  Credit createCreditStatement(String originalText);
}
