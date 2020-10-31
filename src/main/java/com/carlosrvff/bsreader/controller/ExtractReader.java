package com.carlosrvff.bsreader.controller;

import com.carlosrvff.bsreader.converter.BankConverter;
import com.carlosrvff.bsreader.converter.CheetahConverter;
import com.carlosrvff.bsreader.converter.ItauCsvConverter;
import com.carlosrvff.bsreader.converter.ItauSiteConverter;
import com.carlosrvff.bsreader.converter.KbcConverter;
import com.carlosrvff.bsreader.domain.Statement;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
    BankConverter bankConverter = null;
    StringBuilder content = new StringBuilder();
    String line;
    boolean isHeader = true;
    while ((line = bufferedReader.readLine()) != null) {
      if (isHeader) {
        bankConverter = getBankConverter(line);
        isHeader = false;
      } else {
        content.append(line).append(System.lineSeparator());
      }
    }
    if (bankConverter == null) {
      return Collections.emptyList();
    } else {
      return bankConverter.toStatements(content.toString());
    }
  }

  private BankConverter getBankConverter(String header) {
    Queue<BankConverter> queue = buildBankConverterQueue();
    while (!queue.isEmpty()) {
      BankConverter currentBankConverter = queue.poll();
      if (header.equalsIgnoreCase(currentBankConverter.getHeader())) {
        return currentBankConverter;
      }
    }
    throw new IllegalArgumentException("ExtractReader cannot be processed: unknown Bank.");
  }

  private Queue<BankConverter> buildBankConverterQueue() {
    Queue<BankConverter> queue = new LinkedList<>();
    queue.add(new ItauSiteConverter());
    queue.add(new ItauCsvConverter());
    queue.add(new CheetahConverter());
    queue.add(new KbcConverter());
    return queue;
  }
}
