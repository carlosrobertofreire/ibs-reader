package com.github.carlosrvff.bsreader.app;

import com.github.carlosrvff.bsreader.controller.ExtractReader;
import com.github.carlosrvff.bsreader.domain.Statement;
import com.github.carlosrvff.bsreader.controller.ReportGenerator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class BSReaderReport {

  public static void main(String[] args) {
    try {
      String inputFile = System.getProperty("inputFile", getDefaultFile("input.txt"));
      String debitKbFile = System.getProperty("debitKbFile", getDefaultFile("debit-kb.txt"));
      String outputFile = System.getProperty("outputFile", getDefaultFile("output.txt"));
      List<Statement> statements = new ExtractReader().load(inputFile);
      if (statements.isEmpty()) {
        log.info("No statements found.");
        return;
      }
      log.info("Processing data...");
      String content = new ReportGenerator().generate(statements, debitKbFile);
      writeToFile(content, outputFile);
      log.info("Finished!");
    } catch (Exception e) {
      log.fatal("Error executing BSReaderReport:", e);
    }
  }

  private static String getDefaultFile(String expectedFileName) {
    return new StringBuilder(System.getProperty("user.home"))
        .append("/bsreader/")
        .append(expectedFileName)
        .toString();
  }

  private static void writeToFile(String content, String outuputFileName) throws IOException {
    log.info("Writing to " + outuputFileName);
    Files.write(Paths.get(outuputFileName), content.getBytes());
  }
}
