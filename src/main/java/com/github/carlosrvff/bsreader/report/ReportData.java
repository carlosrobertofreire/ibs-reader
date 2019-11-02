package com.github.carlosrvff.bsreader.report;

import com.github.carlosrvff.bsreader.kb.DebitKnowledgeItem;
import com.github.carlosrvff.bsreader.domain.Statement;

import java.util.ArrayList;
import java.util.HashMap;

public class ReportData {

  private ArrayList<Statement> credits = new ArrayList<>();
  private ArrayList<Statement> balances = new ArrayList<>();
  private ArrayList<Statement> unknownDebits = new ArrayList<>();
  private HashMap<DebitKnowledgeItem, ArrayList<Statement>> knownDebits = new HashMap<>();

  public ArrayList<Statement> getCredits() {
    return credits;
  }

  public void addCredit(Statement credit) {
    credits.add(credit);
  }

  public ArrayList<Statement> getBalances() {
    return balances;
  }

  public void addBalance(Statement balance) {
    balances.add(balance);
  }

  public ArrayList<Statement> getUnknownDebits() {
    return unknownDebits;
  }

  public void addUnknownDebit(Statement unknownDebit) {
    unknownDebits.add(unknownDebit);
  }

  public HashMap<DebitKnowledgeItem, ArrayList<Statement>> getKnownDebits() {
    return knownDebits;
  }
}
