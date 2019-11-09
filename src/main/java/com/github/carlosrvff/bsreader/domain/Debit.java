package com.github.carlosrvff.bsreader.domain;

import lombok.Builder;
import lombok.Data;

@Data
public class Debit extends Statement {

  private String store;

  @Override
  protected String getPrefix() {
    return "DEBIT";
  }

  @Override
  protected String getContent() {
    return new StringBuilder(" Store: ").append(store).toString();
  }

  @Builder
  public Debit(String date, String value, String originalText, String store) {
    super(date, value, originalText);
    this.store = store;
  }
}
