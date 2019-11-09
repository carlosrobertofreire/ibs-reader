package com.github.carlosrvff.bsreader.domain;

import com.github.carlosrvff.bsreader.converter.BankConverter;
import com.github.carlosrvff.bsreader.converter.ItauSiteConverter;
import com.github.carlosrvff.bsreader.exception.InvalidStatementException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Extract {

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
    ItauSiteConverter itauSiteConverter = new ItauSiteConverter();
    String line;
    while ((line = bufferedReader.readLine()) != null) {
      processLine(result, itauSiteConverter, line);
    }
  }

  private void processLine(
      List<Statement> result, BankConverter bankConverter, String line) {
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
