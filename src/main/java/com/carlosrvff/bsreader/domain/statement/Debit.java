package com.carlosrvff.bsreader.domain.statement;

import com.carlosrvff.bsreader.domain.Statement;
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
  private String category;

  @Builder
  public Debit(String date, String value, String originalText, String store, String category) {
    super(date, value, originalText);
    if (StringUtils.isNotBlank(store)) {
      this.store = store;
    }
    if (StringUtils.isNotBlank(category)) {
      this.category = category;
    }
  }
}
