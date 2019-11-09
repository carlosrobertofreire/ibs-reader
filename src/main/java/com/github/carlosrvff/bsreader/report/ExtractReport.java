package com.github.carlosrvff.bsreader.report;

import com.github.carlosrvff.bsreader.domain.Balance;
import com.github.carlosrvff.bsreader.domain.Credit;
import com.github.carlosrvff.bsreader.domain.Debit;
import com.github.carlosrvff.bsreader.domain.Statement;
import com.github.carlosrvff.bsreader.kb.DebitKnowledgeBase;
import com.github.carlosrvff.bsreader.kb.DebitKnowledgeItem;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class ExtractReport {

  private static final String SEPARATOR = "-----------------------------------------------------";

  public static String generate(List<Statement> statements, String debitKnowledgeBaseFileName)
      throws IOException {
    StringBuilder content = new StringBuilder();
    ReportData reportData = toReportData(statements, debitKnowledgeBaseFileName);
    appendKnownDebits(content, reportData.getKnownDebits());
    appendUnknownDebits(content, reportData.getUnknownDebits());
    appendCredits(content, reportData.getCredits());
    appendBalances(content, reportData.getBalances());
    return content.toString();
  }

  private static ReportData toReportData(
      List<Statement> statements, String debitKnowledgeBaseFileName) throws IOException {
    ReportData reportData = new ReportData();
    List<DebitKnowledgeItem> debitKnowledgeItems =
        new DebitKnowledgeBase().load(debitKnowledgeBaseFileName);
    for (Statement statement : statements) {
      if (statement instanceof Debit) {
        processDebitStatement(reportData, debitKnowledgeItems, (Debit) statement);
      } else if (statement instanceof Credit) {
        reportData.getCredits().add(statement);
      } else if (statement instanceof Balance) {
        reportData.getBalances().add(statement);
      }
    }
    return reportData;
  }

  private static void processDebitStatement(
      ReportData reportData, List<DebitKnowledgeItem> debitKnowledgeItems, Debit debit) {
    Optional<DebitKnowledgeItem> debitKnowledgeItemOptional =
        getDebitKnowledgeItemOptional(debitKnowledgeItems, debit);
    if (debitKnowledgeItemOptional.isPresent()) {
      if (reportData.getKnownDebits().containsKey(debitKnowledgeItemOptional.get())) {
        reportData.getKnownDebits().get(debitKnowledgeItemOptional.get()).add(debit);
      } else {
        reportData.getKnownDebits().put(debitKnowledgeItemOptional.get(), Arrays.asList(debit));
      }
    } else {
      reportData.getUnknownDebits().add(debit);
    }
  }

  private static Optional<DebitKnowledgeItem> getDebitKnowledgeItemOptional(
      List<DebitKnowledgeItem> debitKnowledgeItems, Debit debit) {
    for (int i = 0; i < debitKnowledgeItems.size(); i++) {
      DebitKnowledgeItem debitKnowledgeItem = debitKnowledgeItems.get(i);
      for (String keyword : debitKnowledgeItem.getKeywords()) {
        if (debit.getStore().equalsIgnoreCase(keyword)) {
          return Optional.of(debitKnowledgeItem);
        }
      }
    }
    return Optional.empty();
  }

  private static void appendBalances(StringBuilder content, List<Statement> balances) {
    append(content, "BALANCES", balances, false);
  }

  private static void appendCredits(StringBuilder content, List<Statement> credits) {
    append(content, "CREDITS", credits, false);
  }

  private static void appendUnknownDebits(StringBuilder content, List<Statement> unknownDebits) {
    append(content, "UNKNOWN DEBITS", unknownDebits, true);
  }

  private static void appendKnownDebits(
      StringBuilder content, HashMap<DebitKnowledgeItem, List<Statement>> knownDebits) {
    knownDebits.forEach(
        (key, value) -> {
          append(content, key.getName(), value, true);
        });
  }

  private static void append(
      StringBuilder content, String title, List<Statement> statements, boolean printValues) {
    if (statements.isEmpty()) {
      return;
    }
    appendBasicInformation(content, title, statements);
    if (printValues) {
      appendValues(content, statements);
    }
  }

  private static void appendBasicInformation(
      StringBuilder content, String title, List<Statement> statements) {
    content
        .append(SEPARATOR)
        .append(System.lineSeparator())
        .append(title)
        .append(System.lineSeparator());
    for (Statement statement : statements) {
      content.append(statement.getOriginalText()).append(System.lineSeparator());
    }
  }

  private static void appendValues(StringBuilder content, List<Statement> statements) {
    content.append(System.lineSeparator());
    for (int i = 0; i < statements.size(); i++) {
      Statement statement = statements.get(i);
      content.append(statement.getValue());
      if (i != statements.size() - 1) {
        content.append("+");
      }
    }
    content.append(System.lineSeparator());
  }
}
