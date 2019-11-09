package com.github.carlosrvff.bsreader.domain;

import lombok.Builder;
import lombok.Data;

@Data
public class Debit extends Statement {

  private String store;

  @Builder
  public Debit(String date, String value, String originalText, String store) {
    super(date, value, originalText);
    this.store = store;
  }
}
