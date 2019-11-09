package com.github.carlosrvff.bsreader.domain;

import lombok.Builder;

public class Balance extends Statement {

  @Override
  protected String getPrefix() {
    return "BALANCE";
  }

  @Override
  protected String getContent() {
    return "";
  }

  @Builder
  public Balance(String date, String value, String originalText) {
    super(date, value, originalText);
  }
}
