package com.github.carlosrvff.apps;

import com.github.carlosrvff.bsreader.converter.domain.Balance;
import com.github.carlosrvff.bsreader.converter.domain.Credit;
import com.github.carlosrvff.bsreader.converter.domain.Debit;
import com.github.carlosrvff.bsreader.converter.kb.DebitKnowledgeBase;
import com.github.carlosrvff.bsreader.converter.kb.DebitKnowledgeItem;
import com.github.carlosrvff.bsreader.converter.domain.Extract;
import com.github.carlosrvff.bsreader.converter.report.ReportData;
import com.github.carlosrvff.bsreader.converter.domain.Statement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class IBSReaderReport {

	private static final String SEPARATOR = "------------------------------------------------------------------------";

	public static void main(String[] args) {
		ArrayList<Statement> statements = Extract.getStatements();
		if (statements.isEmpty()) {
			System.out.println("No statements found.");
			return;
		}
		System.out.println(SEPARATOR);
		System.out.println("Processing data...");
		
		StringBuilder content = new StringBuilder();	
		
		ReportData reportData = processData(statements);		
		
		appendKnownDebits(content, reportData.getKnownDebits());
		appendUnknownDebits(content, reportData.getUnknownDebits());
		appendCredits(content, reportData.getCredits());
		appendBalances(content, reportData.getBalances());

		writeToFile(content);

		System.out.println(SEPARATOR);
		System.out.println("Finished!");
	}

	private static ReportData processData(ArrayList<Statement> statements) {
		ReportData reportData = new ReportData();
		ArrayList<DebitKnowledgeItem> debitKnowledgeItems = DebitKnowledgeBase.getDebitKnowledgeItems();		
		for (Statement statement : statements) {
			if (statement instanceof Debit) {
				processDebitStatement(reportData, debitKnowledgeItems, statement);
			} else if (statement instanceof Credit) {
				reportData.addCredit(statement);
			} else if (statement instanceof Balance) {
				reportData.addBalance(statement);
			}
		}
		return reportData;
	}

	private static void processDebitStatement(ReportData reportData, ArrayList<DebitKnowledgeItem> debitKnowledgeItems, Statement statement) {
		Debit debit = (Debit) statement;
		boolean found = false;
		for (int i = 0; i < debitKnowledgeItems.size() && !found; i++) {
			DebitKnowledgeItem debitKnowledgeItem = debitKnowledgeItems.get(i);
			for (String keyword : debitKnowledgeItem.getKeywords()) {
				if (debit.getStore().toUpperCase().contains(keyword.toUpperCase())) {
					found = true;
					if (reportData.getKnownDebits().containsKey(debitKnowledgeItem)) {
						reportData.getKnownDebits().get(debitKnowledgeItem).add(debit);
					} else {
						ArrayList<Statement> debits = new ArrayList<Statement>();
						debits.add(debit);
						reportData.getKnownDebits().put(debitKnowledgeItem, debits);
					}
					break;
				}
			}
		}
		if (!found) {
			reportData.addUnknownDebit(debit);
		}
	}

	private static void writeToFile(StringBuilder content) {
		String userHome = System.getProperty("user.home");
		String fileName = userHome + "/IBSReader/output.txt";
		System.out.println(SEPARATOR);
		System.out.println("Writing to " + fileName);
		try {
			Files.write(Paths.get(fileName), content.toString().getBytes());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void appendBalances(StringBuilder content, ArrayList<Statement> balances) {
		append(content, "BALANCES", balances, false);
	}

	private static void appendCredits(StringBuilder content, ArrayList<Statement> credits) {
		append(content, "CREDITS", credits, false);
	}

	private static void appendUnknownDebits(StringBuilder content, ArrayList<Statement> unknownDebits) {
		append(content, "UNKNOWN DEBITS", unknownDebits, true);
	}

	private static void appendKnownDebits(StringBuilder content, HashMap<DebitKnowledgeItem, ArrayList<Statement>> knownDebits) {
		knownDebits.forEach((k, v) -> {
			append(content, k.getName(), v, true);
		});
	}
	
	private static void append(StringBuilder content, String title, ArrayList<Statement> statements, boolean printValues) {
		if (statements.isEmpty())
			return;

		content.append(SEPARATOR);
		content.append(System.lineSeparator());
		content.append(title);
		content.append(System.lineSeparator());
		for (Statement statement : statements) {
			content.append(statement.getOriginalText());
			content.append(System.lineSeparator());
		}

		if (printValues) {
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

}
