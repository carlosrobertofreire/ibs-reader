package com.github.carlosrvff.bsreader.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Statement {

  private String date;
  private String value;
  private String originalText;

  protected abstract String getPrefix();

  protected abstract String getContent();

  @Override
  public String toString() {
    return new StringBuilder(getPrefix())
        .append(getPrefix())
        .append(" ")
        .append("Date: ")
        .append(getDate())
        .append(getContent())
        .append(" Value: ")
        .append(getValue())
        .toString();
  }
}
