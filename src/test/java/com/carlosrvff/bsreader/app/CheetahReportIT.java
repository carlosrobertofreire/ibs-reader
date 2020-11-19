package com.carlosrvff.bsreader.app;

public class CheetahReportIT extends BankReportIT {

  @Override
  protected String getInputFileName() {
    return "input-sample-cheetah.txt";
  }

  @Override
  protected String getOutputFileName() {
    return "output-sample-cheetah.txt";
  }
}
