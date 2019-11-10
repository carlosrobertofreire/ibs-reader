package com.github.carlosrvff.bsreader.report;

import com.github.carlosrvff.bsreader.domain.Statement;
import com.github.carlosrvff.bsreader.kb.DebitKnowledgeItem;
import java.util.HashMap;
import java.util.List;
import lombok.Data;
import lombok.Singular;

@Data
public class ReportData {

  @Singular private List<Statement> credits;
  @Singular private List<Statement> balances;
  @Singular private List<Statement> unknownDebits;
  @Singular private HashMap<DebitKnowledgeItem, List<Statement>> knownDebits;
}
