package com.carlosrvff.bsreader.converter;

import com.carlosrvff.bsreader.domain.Statement;
import com.carlosrvff.bsreader.exception.InvalidStatementException;
import java.util.List;
import lombok.NonNull;

public interface StatementConverter {

  Statement toStatement(@NonNull String line) throws InvalidStatementException;

  List<Statement> toStatements(@NonNull String content) throws InvalidStatementException;

  List<Statement> toStatements(@NonNull String[] lines) throws InvalidStatementException;

  String getHeader();

}
