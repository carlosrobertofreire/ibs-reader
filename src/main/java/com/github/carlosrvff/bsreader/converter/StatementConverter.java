package com.github.carlosrvff.bsreader.converter;

import com.github.carlosrvff.bsreader.domain.Statement;
import com.github.carlosrvff.bsreader.exception.InvalidStatementException;
import lombok.NonNull;

public interface StatementConverter {

  Statement toStatement(@NonNull String text) throws InvalidStatementException;

  String getHeader();

}
