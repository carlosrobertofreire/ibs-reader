package com.github.carlosrvff.bsreader.converter;

import com.github.carlosrvff.bsreader.domain.Balance;
import com.github.carlosrvff.bsreader.domain.Credit;
import com.github.carlosrvff.bsreader.domain.Debit;
import com.github.carlosrvff.bsreader.domain.Statement;
import com.github.carlosrvff.bsreader.exception.InvalidStatementException;
import lombok.NonNull;

public class ItauSiteConverter implements BankConverter {

  @Override
  public Statement toStatement(@NonNull String text) throws InvalidStatementException {
    if (isHeader(text)) {
      throw new InvalidStatementException("Header cannot be considered a Statement.", text);
    }

    String[] parts = text.split("\t");
    if (parts.length < 6) {
      throw new InvalidStatementException("Incorrect numbers of fields", text);
    }

    if (parts.length >= 8) {
      return Balance.builder().date(parts[0]).value(parts[7]).originalText(text).build();
    }

    String date = parts[0];
    String details = parts[3].trim();
    String value = parts[5];

    if (parts.length > 6) {
      return Debit.builder().date(date).store(details).value(value).originalText(text).build();
    } else {
      return Credit.builder().date(date).from(details).value(value).originalText(text).build();
    }
  }

  @Override
  public String getHeader() {
    return "Data\t \t \tLançamento\tAg/Origem\tValor (R$)\t \tSaldo (R$)\t ";
  }

  @Override
  public boolean isHeader(@NonNull String text) {
    return getHeader().toUpperCase().trim().equals(text.toUpperCase().trim());
  }
}
