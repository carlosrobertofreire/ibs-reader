package com.carlosrvff.bsreader.domain.statement;

import com.carlosrvff.bsreader.domain.Statement;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper=true)
public class Credit extends Statement {

  private String from;

  @Builder
  public Credit(String date, String value, String originalText, String from) {
    super(date, value, originalText);
    this.from = from;
  }
}
