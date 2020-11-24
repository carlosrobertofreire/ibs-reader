package com.carlosrvff.bsreader.domain.statement;

import com.carlosrvff.bsreader.domain.Statement;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper=true)
public class Balance extends Statement {

  @Builder
  public Balance(String date, String value, String originalText) {
    super(date, value, originalText);
  }
}
