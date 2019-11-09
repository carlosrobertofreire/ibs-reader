package com.github.carlosrvff.bsreader.app;

import com.github.carlosrvff.bsreader.domain.Extract;
import com.github.carlosrvff.bsreader.domain.Statement;
import com.github.carlosrvff.bsreader.report.ExtractReport;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class BSReaderReport {

  public static void main(String[] args) throws IOException {
    List<Statement> statements = new Extract().load(args[0]);
    if (statements.isEmpty()) {
      log.info("No statements found.");
      return;
    }
    log.info("Processing data...");
    String content = new ExtractReport().generate(statements, args[1]);
    writeToFile(content, args[2]);
    log.info("Finished!");
  }

  private static void writeToFile(String content, String outuputFileName) throws IOException {
    log.info("Writing to " + outuputFileName);
    Files.write(Paths.get(outuputFileName), content.getBytes());
  }
}
