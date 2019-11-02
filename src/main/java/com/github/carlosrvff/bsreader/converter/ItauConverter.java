package com.github.carlosrvff.bsreader.converter;

import com.github.carlosrvff.bsreader.domain.Balance;
import com.github.carlosrvff.bsreader.exception.InvalidStatementException;
import com.github.carlosrvff.bsreader.domain.Credit;
import com.github.carlosrvff.bsreader.domain.Debit;
import com.github.carlosrvff.bsreader.domain.Statement;

public class ItauConverter implements StatementConverter {

  public static final String HEADER =
      "Data\t \t \tLançamento\tAg/Origem\tValor (R$)\t \tSaldo (R$)\t ";

  @Override
  public Statement toStatement(String text) throws InvalidStatementException {
    if (text == null || text.isEmpty()) {
      throw new InvalidStatementException("There is no statement value to convert", text);
    }

    if (isHeader(text)) {
      throw new InvalidStatementException("Invalid statement", text);
    }

    String[] parts = text.split("\t");
    if (parts.length < 6) {
      throw new InvalidStatementException("Incorrect numbers of fields", text);
    }

    if (parts.length >= 8) {
      return new Balance(parts[0], parts[7], text);
    }

    String date = parts[0];
    String details = parts[3].trim();
    String value = parts[5];

    if (parts.length > 6) {
      return new Debit(date, details, value, text);
    } else {
      return new Credit(date, details, value, text);
    }
  }

  @Override
  public boolean isHeader(String text) {
    return HEADER.toUpperCase().trim().equals(text.toUpperCase().trim());
  }
}
