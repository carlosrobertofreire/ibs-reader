package com.carlosrvff.bsreader.app;

public class RevolutReportIT extends BankReportIT {

  @Override
  protected String getInputFileName() {
    return "input-sample-revolut.txt";
  }

  @Override
  protected String getOutputFileName() {
    return "output-sample-revolut.txt";
  }
}
