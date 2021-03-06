package com.carlosrvff.bsreader.domain.kb;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DebitKnowledgeItem {

  private String name;
  private List<String> keywords;
}
