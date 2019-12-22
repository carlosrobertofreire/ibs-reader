package com.github.carlosrvff.bsreader.converter;

import com.github.carlosrvff.bsreader.domain.Statement;
import com.github.carlosrvff.bsreader.exception.InvalidStatementException;
import java.util.List;
import lombok.NonNull;

public interface StatementConverter {

  Statement toStatement(@NonNull String line) throws InvalidStatementException;

  List<Statement> toStatements(@NonNull String content) throws InvalidStatementException;

  String getHeader();

}
