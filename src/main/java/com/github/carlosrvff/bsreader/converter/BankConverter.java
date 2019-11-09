package com.github.carlosrvff.bsreader.converter;

import com.github.carlosrvff.bsreader.domain.Statement;
import com.github.carlosrvff.bsreader.exception.InvalidStatementException;

public interface BankConverter {

  Statement toStatement(String text) throws InvalidStatementException;

  String getHeader();

}
