package com.github.carlosrvff.bsreader.kb;

import java.util.ArrayList;

public class DebitKnowledgeItem {

  private String name;
  private ArrayList<String> keywords;

  public DebitKnowledgeItem() {
    keywords = new ArrayList<String>();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ArrayList<String> getKeywords() {
    return keywords;
  }

  public void setKeywords(ArrayList<String> keywords) {
    this.keywords = keywords;
  }
}
