package com.github.carlosrvff.bsreader.converter.converter;

import com.github.carlosrvff.bsreader.converter.domain.Statement;
import com.github.carlosrvff.bsreader.converter.exception.InvalidStatementException;

public interface StatementConverter {

  Statement toStatement(String text) throws InvalidStatementException;

  boolean isHeader(String text);

}
