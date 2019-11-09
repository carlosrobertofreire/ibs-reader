package com.github.carlosrvff.bsreader.domain;

import lombok.Builder;

public class Balance extends Statement {

  @Builder
  public Balance(String date, String value, String originalText) {
    super(date, value, originalText);
  }
}
