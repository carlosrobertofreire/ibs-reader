package com.carlosrvff.bsreader.controller;

import com.carlosrvff.bsreader.domain.Bank;
import com.carlosrvff.bsreader.domain.BankFactory;
import com.carlosrvff.bsreader.domain.Statement;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ExtractReader {

  public List<Statement> load(String fileName) throws IOException {
    log.info("Loading statements from " + fileName);
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
      return load(bufferedReader);
    }
  }

  private List<Statement> load(BufferedReader bufferedReader) throws IOException {
    Bank bank = null;
    StringBuilder content = new StringBuilder();
    String line;
    while ((line = bufferedReader.readLine()) != null) {
      content.append(line).append(System.lineSeparator());
    }
    bank = getBank(content.toString());
    if (bank == null) {
      return Collections.emptyList();
    } else {
      return bank.getStatements();
    }
  }

  private Bank getBank(String header) {
    return BankFactory.getBank(header);
  }
}