package com.carlosrvff.bsreader.converter;

import com.carlosrvff.bsreader.domain.Credit;
import com.carlosrvff.bsreader.domain.Debit;
import com.carlosrvff.bsreader.domain.Statement;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

public class RevolutConverter extends BankConverter {

  @Override
  public Statement toStatement(@NonNull String line) {
    validate(line);
    String[] parts = line.split(getSeparator());

    if (parts.length != 9) {
      throw new IllegalArgumentException("Incorrect numbers of fields: " + line);
    }

    String date = parts[0];
    String details = parts[1];
    String paidOut = parts[2];
    String paidIn = parts[3];

    if (StringUtils.isNotBlank(paidOut)) {
      return Debit.builder().date(date).store(details).value(paidOut).originalText(line).build();
    } else if (StringUtils.isNotBlank(paidIn)) {
      return Credit.builder().date(date).from(details).value(paidIn).originalText(line).build();
    } else {
      throw new IllegalArgumentException("Invalid transaction: " + line);
    }
  }

  @Override
  public String getHeader() {
    return "Completed Date;Reference;Paid Out (EUR);Paid In (EUR);Exchange Out;Exchange In; Balance (EUR);Exchange Rate;Category";
  }

  @Override
  protected String getSeparator() {
    return ";";
  }
}
