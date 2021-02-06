package com.carlosrvff.bsreader.converter;

import com.carlosrvff.bsreader.domain.Statement;
import com.carlosrvff.bsreader.domain.statement.Credit;
import com.carlosrvff.bsreader.domain.statement.Debit;
import org.apache.commons.lang3.StringUtils;

public class RevolutStatementConverter extends StatementConverter {

  @Override
  public String getSeparator() {
    return ";";
  }

  @Override
  protected boolean arePartsInvalid(String[] parts) {
    return parts.length != 9;
  }

  @Override
  protected Statement toStatement(String[] parts, String line) {
    String date = parts[0];
    String details = parts[1];
    String paidOut = parts[2];
    String paidIn = parts[3];
    String category = parts[8];

    if (StringUtils.isNotBlank(paidOut)) {
      return Debit.builder()
          .date(date)
          .store(details)
          .value(paidOut)
          .originalText(line)
          .category(category)
          .build();
    } else if (StringUtils.isNotBlank(paidIn)) {
      return Credit.builder().date(date).from(details).value(paidIn).originalText(line).build();
    } else {
      throw new IllegalArgumentException("Invalid transaction: " + line);
    }
  }
}
