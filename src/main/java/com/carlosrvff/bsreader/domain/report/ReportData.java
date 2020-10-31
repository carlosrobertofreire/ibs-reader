package com.carlosrvff.bsreader.domain.report;

import com.carlosrvff.bsreader.domain.Statement;
import com.carlosrvff.bsreader.domain.kb.DebitKnowledgeItem;
import java.util.ArrayList;
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
  private HashMap<DebitKnowledgeItem, ArrayList<Statement>> knownDebits;
}
