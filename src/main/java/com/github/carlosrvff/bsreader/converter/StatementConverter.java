package com.github.carlosrvff.bsreader.converter;

import com.github.carlosrvff.bsreader.domain.Statement;
import com.github.carlosrvff.bsreader.exception.InvalidStatementException;

public interface StatementConverter {

  Statement toStatement(String text) throws InvalidStatementException;

  boolean isHeader(String text);

}
