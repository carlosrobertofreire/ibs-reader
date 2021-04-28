package com.carlosrvff.bsreader.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.carlosrvff.bsreader.controller.ExtractReader;
import com.carlosrvff.bsreader.controller.ReportGenerator;
import com.carlosrvff.bsreader.domain.Statement;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

abstract class BankReportIT {

  public static final String TEST_RESOURCES_FOLDER = "src/test/resources/";
  public static final String DEBIT_KB_FILE_NAME = TEST_RESOURCES_FOLDER + "debit-kb.org";

  protected abstract String getInputFileName();

  protected abstract String getOutputFileName();

  @Test
  void run() throws IOException {
    String expectedContent = loadExpectedContentFromOutputFile();

    String generatedContent = generateContentFromInputFile();

    assertEquals(expectedContent, generatedContent);
  }

  private String loadExpectedContentFromOutputFile() throws IOException {
    StringBuilder expectedContent = new StringBuilder();
    try (BufferedReader bufferedReader =
        new BufferedReader(new FileReader(TEST_RESOURCES_FOLDER + getOutputFileName()))) {
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        expectedContent.append(line).append(System.lineSeparator());
      }
    }
    return expectedContent.toString();
  }

  private String generateContentFromInputFile() throws IOException {
    List<Statement> statements = new ExtractReader().load(TEST_RESOURCES_FOLDER + getInputFileName());
    return new ReportGenerator().generate(statements, DEBIT_KB_FILE_NAME);
  }
}
