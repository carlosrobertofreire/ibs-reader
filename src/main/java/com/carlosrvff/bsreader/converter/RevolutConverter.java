package com.carlosrvff.bsreader.converter;

import com.carlosrvff.bsreader.domain.Debit;
import com.carlosrvff.bsreader.domain.Statement;
import com.carlosrvff.bsreader.exception.InvalidStatementException;
import lombok.NonNull;

public class RevolutConverter extends BankConverter {

  public static final String SEPARATOR = ";";

  @Override
  public Statement toStatement(@NonNull String line) throws InvalidStatementException {
    validate(line);
    String[] parts = line.split(SEPARATOR);

    String date = parts[0];
    String details = parts[1];
    String value = parts[2];

    return Debit.builder()
        .date(date)
        .store(details)
        .value(value)
        .originalText(line)
        .build();
  }

  @Override
  public String getHeader() {
    return "Completed Date;Reference;Paid Out (EUR);Paid In (EUR);Exchange Out;Exchange In; Balance (EUR);Exchange Rate;Category";
  }
}
