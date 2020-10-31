package com.carlosrvff.bsreader.converter;

import com.carlosrvff.bsreader.domain.Balance;
import com.carlosrvff.bsreader.domain.Credit;
import com.carlosrvff.bsreader.domain.Debit;
import com.carlosrvff.bsreader.domain.Statement;
import com.carlosrvff.bsreader.exception.InvalidStatementException;
import java.util.HashSet;
import lombok.NonNull;

public class ItauCsvConverter extends BankConverter {

  @Override
  public Statement toStatement(@NonNull String line) throws InvalidStatementException {
    validate(line);
    String[] parts = line.split(";");
    if (parts.length < 3) {
      throw new InvalidStatementException("Incorrect numbers of fields.", line);
    }
    String date = parts[0];
    String details = parts[1];
    String value = parts[2];

    if (getHashBalanceDetailsValues().contains(details)) {
      return Balance.builder().date(date).value(value).originalText(line).build();
    } else {
      if (isDebitValue(value)) {
        return Debit.builder()
            .date(date)
            .store(details)
            .value(removeDebitSymbol(value))
            .originalText(line)
            .build();
      } else {
        return Credit.builder().date(date).from(details).value(value).originalText(line).build();
      }
    }
  }

  private HashSet<String> getHashBalanceDetailsValues() {
    HashSet<String> result = new HashSet<>();
    result.add("RES APLIC AUT MAIS");
    result.add("SALDO APLIC AUT MAIS");
    result.add("SALDO FINAL");
    result.add("SALDO PARCIAL");
    result.add("SALDO INICIAL");
    return result;
  }

  @Override
  public String getHeader() {
    return "data;detalhe;valor";
  }

  private String removeDebitSymbol(String value) {
    return value.substring(1);
  }
}
