package com.github.carlosrvff.bsreader.controller;

import com.github.carlosrvff.bsreader.converter.BankConverter;
import com.github.carlosrvff.bsreader.converter.CheetahConverter;
import com.github.carlosrvff.bsreader.converter.StatementConverter;
import com.github.carlosrvff.bsreader.converter.ItauCsvConverter;
import com.github.carlosrvff.bsreader.converter.ItauSiteConverter;
import com.github.carlosrvff.bsreader.domain.Statement;
import com.github.carlosrvff.bsreader.exception.InvalidStatementException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ExtractReader {

  public List<Statement> load(String fileName) throws IOException {
    log.info("Loading statements from " + fileName);
    List<Statement> result = new ArrayList<>();
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
      processContent(result, bufferedReader);
    }
    return result;
  }

  private void processContent(List<Statement> result, BufferedReader bufferedReader)
      throws IOException {
    StatementConverter bankConverter = null;
    boolean isHeader = true;
    String line;
    while ((line = bufferedReader.readLine()) != null) {
      if (isHeader) {
        bankConverter = getBankConverter(line);
        isHeader = false;
      } else {
        processLine(result, bankConverter, line);
      }
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
    return queue;
  }

  private void processLine(List<Statement> result, StatementConverter bankConverter, String line) {
    try {
      Statement statement = bankConverter.toStatement(line);
      result.add(statement);
    } catch (InvalidStatementException e) {
      log.fatal(e.getMessage());
      if (e.getCause() != null) {
        log.fatal("  Original exception: " + e.getCause().getMessage());
      }
    }
  }
}
