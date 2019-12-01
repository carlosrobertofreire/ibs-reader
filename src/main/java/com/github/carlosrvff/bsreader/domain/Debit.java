package com.github.carlosrvff.bsreader.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper=true)
public class Debit extends Statement {

  private String store;

  @Builder
  public Debit(String date, String value, String originalText, String store) {
    super(date, value, originalText);
    if (StringUtils.isNotBlank(store)) {
      this.store = store;
    }
  }
}
