package com.carlosrvff.bsreader.app;

import com.carlosrvff.bsreader.controller.ExtractReader;
import com.carlosrvff.bsreader.controller.ReportGenerator;
import com.carlosrvff.bsreader.domain.Statement;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;

@Log4j2
public class BSReaderReport {

  public static void main(String[] args) {
    try {
      String inputFile =
          getValue(System.getenv("BSREADER_INPUT_FILE"), getDefaultFile("input.txt"));
      String debitKbFile =
          getValue(System.getenv("BSREADER_DEBIT_KB_FILE"), getDefaultFile("debit-kb.txt"));
      String outputFile =
          getValue(System.getenv("BSREADER_OUTPUT_FILE"), getDefaultFile("output.txt"));
      List<Statement> statements = new ExtractReader().load(inputFile);
      if (statements.isEmpty()) {
        log.info("No statements found.");
        return;
      }
      log.info("Processing data...");
      String content = new ReportGenerator().generate(statements, debitKbFile);
      writeToFile(content, outputFile);
      log.info("Finished! " + statements.size() + " statements read!");
    } catch (Exception e) {
      log.fatal("Error executing BSReaderReport:", e);
    }
  }

  private static String getValue(String inputValue, String defaultValue) {
    if (Strings.isBlank(defaultValue)) {
      throw new IllegalArgumentException("Argument defaultValue cannot be blank.");
    }
    if (Strings.isNotBlank(inputValue)) {
      return inputValue;
    } else {
      return defaultValue;
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
