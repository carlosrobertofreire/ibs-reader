package com.carlosrvff.bsreader.converter;

import com.carlosrvff.bsreader.domain.Statement;
import com.carlosrvff.bsreader.domain.statement.Balance;
import com.carlosrvff.bsreader.domain.statement.Credit;
import com.carlosrvff.bsreader.domain.statement.Debit;
import java.util.HashSet;

public class ItauCsvConverter extends BankConverter {

  @Override
  protected boolean arePartsInvalid(String[] parts) {
    return parts.length < 3;
  }

  @Override
  protected Statement toStatement(String[] parts, String line) {
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

  @Override
  protected String getSeparator() {
    return ";";
  }

  private String removeDebitSymbol(String value) {
    return value.substring(1);
  }
}
