package com.github.carlosrvff.bsreader.report;

import com.github.carlosrvff.bsreader.domain.Statement;
import com.github.carlosrvff.bsreader.kb.DebitKnowledgeItem;
import java.util.HashMap;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportData {

  private List<Statement> credits;
  private List<Statement> balances;
  private List<Statement> unknownDebits;
  private HashMap<DebitKnowledgeItem, List<Statement>> knownDebits;
}
