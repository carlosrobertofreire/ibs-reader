package com.carlosrvff.bsreader.app;

public class KbcReportIT extends BankReportIT{

  @Override
  protected String getInputFileName() {
    return "input-sample-kbc.txt";
  }

  @Override
  protected String getOutputFileName() {
    return "output-sample-kbc.txt";
  }
}
