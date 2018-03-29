package com.carlosrobertofreire.apps;

import com.carlosrobertofreire.ibsreader.Balance;
import com.carlosrobertofreire.ibsreader.Credit;
import com.carlosrobertofreire.ibsreader.Debit;
import com.carlosrobertofreire.ibsreader.DebitKnowledgeBase;
import com.carlosrobertofreire.ibsreader.DebitKnowledgeItem;
import com.carlosrobertofreire.ibsreader.Extract;
import com.carlosrobertofreire.ibsreader.Statement;

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

		StringBuilder output = new StringBuilder();

		ArrayList<Statement> credits = new ArrayList<Statement>();
		ArrayList<Statement> balances = new ArrayList<Statement>();
		ArrayList<Statement> unknownDebits = new ArrayList<Statement>();
		HashMap<DebitKnowledgeItem, ArrayList<Statement>> knownDebits = new HashMap<DebitKnowledgeItem, ArrayList<Statement>>();

		ArrayList<DebitKnowledgeItem> debitKnowledgeItems = DebitKnowledgeBase.getDebitKnowledgeItems();
		for (Statement statement : statements) {
			if (statement instanceof Debit) {
				Debit debit = (Debit) statement;
				boolean found = false;
				for (int i = 0; i < debitKnowledgeItems.size() && !found; i++) {
					DebitKnowledgeItem debitKnowledgeItem = debitKnowledgeItems.get(i);
					for (String keyword : debitKnowledgeItem.getKeywords()) {
						if (debit.getStore().toUpperCase().contains(keyword.toUpperCase())) {
							found = true;
							if (knownDebits.containsKey(debitKnowledgeItem)) {
								knownDebits.get(debitKnowledgeItem).add(debit);
							} else {
								ArrayList<Statement> debits = new ArrayList<Statement>();
								debits.add(debit);
								knownDebits.put(debitKnowledgeItem, debits);
							}
							break;
						}
					}
				}
				if (!found) {
					unknownDebits.add(debit);
				}
			} else if (statement instanceof Credit) {
				credits.add((Credit) statement);
			} else if (statement instanceof Balance) {
				balances.add((Balance) statement);
			}
		}

		appendKnownDebits(output, knownDebits);
		appendUnknownDebits(output, unknownDebits);
		appendCredits(output, credits);
		appendBalances(output, balances);

		writeToFile(output);

		System.out.println(SEPARATOR);
		System.out.println("Finished!");
	}

	private static void writeToFile(StringBuilder output) {
		String userHome = System.getProperty("user.home");
		String fileName = userHome + "/IBSReader/output.txt";
		System.out.println(SEPARATOR);
		System.out.println("Writing to " + fileName);
		try {
			Files.write(Paths.get(fileName), output.toString().getBytes());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void appendBalances(StringBuilder output, ArrayList<Statement> balances) {
		append(output, "BALANCES", balances, false);
	}

	private static void appendCredits(StringBuilder output, ArrayList<Statement> credits) {
		append(output, "CREDITS", credits, false);
	}

	private static void appendUnknownDebits(StringBuilder output, ArrayList<Statement> unknownDebits) {
		append(output, "UNKNOWN DEBITS", unknownDebits, true);
	}

	private static void appendKnownDebits(StringBuilder output, HashMap<DebitKnowledgeItem, ArrayList<Statement>> knownDebits) {
		knownDebits.forEach((k, v) -> {
			append(output, k.getName(), v, true);
		});
	}
	
	private static void append(StringBuilder output, String title, ArrayList<Statement> statements, boolean printValues) {
		if (statements.isEmpty())
			return;

		output.append(SEPARATOR);
		output.append(System.lineSeparator());
		output.append(title);
		output.append(System.lineSeparator());
		for (Statement statement : statements) {
			output.append(statement.getOriginalText());
			output.append(System.lineSeparator());
		}

		if (printValues) {
			output.append(System.lineSeparator());
			for (int i = 0; i < statements.size(); i++) {
				Statement statement = statements.get(i);
				output.append(statement.getValue());
				if (i != statements.size() - 1) {
					output.append("+");
				}
			}
			output.append(System.lineSeparator());
		}
	}

}
