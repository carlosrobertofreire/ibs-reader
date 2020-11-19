package com.carlosrvff.bsreader.app;

public class ItauSiteReportIT extends BankReportIT {

  @Override
  protected String getInputFileName() {
    return "input-sample-itau-site.txt";
  }

  @Override
  protected String getOutputFileName() {
    return "output-sample-itau-site.txt";
  }
}
