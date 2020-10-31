package com.carlosrvff.bsreader.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.carlosrvff.bsreader.domain.Statement;
import com.carlosrvff.bsreader.controller.ExtractReader;
import com.carlosrvff.bsreader.controller.ReportGenerator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

class BSReaderReportIT {

  public static final String TEST_RESOURCES_FOLDER = "src/test/resources/";
  public static final String DEBIT_KB_FILE_NAME = TEST_RESOURCES_FOLDER + "debit-kb.txt";

  @Test
  void runWhenFileIsCheetah() throws IOException {
    String expectedContent = loadExpectedContentFrom("output-sample-cheetah.txt");

    String generatedContent = generateContentFrom("input-sample-cheetah.txt");

    assertEquals(expectedContent, generatedContent);
  }

  private String loadExpectedContentFrom(String outputFileName) throws IOException {
    StringBuilder expectedContent = new StringBuilder();
    try (BufferedReader bufferedReader =
        new BufferedReader(new FileReader(TEST_RESOURCES_FOLDER + outputFileName))) {
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        expectedContent.append(line).append(System.lineSeparator());
      }
    }
    return expectedContent.toString();
  }

  private String generateContentFrom(String inputFileName) throws IOException {
    List<Statement> statements = new ExtractReader().load(TEST_RESOURCES_FOLDER + inputFileName);
    return new ReportGenerator().generate(statements, DEBIT_KB_FILE_NAME);
  }

  @Test
  void runWhenFileIsItauCsv() throws IOException {
    String expectedContent = loadExpectedContentFrom("output-sample-itau-csv.txt");

    String generatedContent = generateContentFrom("input-sample-itau-csv.txt");

    assertEquals(expectedContent, generatedContent);
  }

  @Test
  void runWhenFileIsItauSite() throws IOException {
    String expectedContent = loadExpectedContentFrom("output-sample-itau-site.txt");

    String generatedContent = generateContentFrom("input-sample-itau-site.txt");

    assertEquals(expectedContent, generatedContent);
  }

  @Test
  void runWhenFileIsKbc() throws IOException {
    String expectedContent = loadExpectedContentFrom("output-sample-kbc.txt");

    String generatedContent = generateContentFrom("input-sample-kbc.txt");

    assertEquals(expectedContent, generatedContent);
  }
}
