package com.github.carlosrvff.bsreader.kb;

import java.util.ArrayList;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DebitKnowledgeItem {

  private String name;
  private ArrayList<String> keywords;
}
