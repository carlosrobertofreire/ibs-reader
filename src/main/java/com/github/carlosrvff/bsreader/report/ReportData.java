package com.github.carlosrvff.bsreader.report;

import com.github.carlosrvff.bsreader.domain.Statement;
import com.github.carlosrvff.bsreader.kb.DebitKnowledgeItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lombok.Getter;

@Getter
public class ReportData {

  private List<Statement> credits = new ArrayList<>();
  private List<Statement> balances = new ArrayList<>();
  private List<Statement> unknownDebits = new ArrayList<>();
  private HashMap<DebitKnowledgeItem, List<Statement>> knownDebits = new HashMap<>();
}
