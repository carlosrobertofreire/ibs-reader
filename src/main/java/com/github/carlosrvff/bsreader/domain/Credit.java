package com.github.carlosrvff.bsreader.domain;

import lombok.Builder;
import lombok.Data;

@Data
public class Credit extends Statement {

  private String from;

  @Override
  protected String getPrefix() {
    return "CREDIT";
  }

  @Override
  protected String getContent() {
    return new StringBuilder(" From: ").append(getFrom()).toString();
  }

  @Builder
  public Credit(String date, String value, String originalText, String from) {
    super(date, value, originalText);
    this.from = from;
  }
}
