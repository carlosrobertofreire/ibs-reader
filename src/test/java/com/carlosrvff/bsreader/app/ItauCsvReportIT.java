package com.carlosrvff.bsreader.app;

public class ItauCsvReportIT extends BankReportIT {

  @Override
  protected String getInputFileName() {
    return "input-sample-itau-csv.txt";
  }

  @Override
  protected String getOutputFileName() {
    return "output-sample-itau-csv.txt";
  }
}
