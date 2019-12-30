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
}
