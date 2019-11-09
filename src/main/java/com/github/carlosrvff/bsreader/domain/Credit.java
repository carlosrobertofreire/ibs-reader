package com.github.carlosrvff.bsreader.domain;

import lombok.Builder;
import lombok.Data;

@Data
public class Credit extends Statement {

  private String from;

  @Builder
  public Credit(String date, String value, String originalText, String from) {
    super(date, value, originalText);
    this.from = from;
  }
}
