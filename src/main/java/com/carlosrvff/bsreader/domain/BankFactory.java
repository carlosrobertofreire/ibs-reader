package com.carlosrvff.bsreader.domain;

import com.carlosrvff.bsreader.domain.bank.Cheetah;
import com.carlosrvff.bsreader.domain.bank.ItauCsv;
import org.apache.commons.lang3.StringUtils;

public class BankFactory {

  public static Bank getBank(String statementsText) {
    if (StringUtils.isBlank(statementsText)) {
      throw new IllegalArgumentException("StatementsText should have a value.");
    }
    String header = getHeader(statementsText);
    if (header.equalsIgnoreCase(Cheetah.getHeader())) {
      return new Cheetah(statementsText);
    } else if (header.equalsIgnoreCase(ItauCsv.getHeader())) {
      return new ItauCsv(statementsText);
    } else {
      throw new IllegalArgumentException("Invalid header: " + header);
    }
  }

  private static String getHeader(String statementsText) {
    String[] lines = statementsText.split(System.lineSeparator());
    return lines[0];
  }
}