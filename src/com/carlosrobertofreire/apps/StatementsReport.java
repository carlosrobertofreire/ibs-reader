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

public class StatementsReport {

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

        ArrayList<Credit> credits = new ArrayList<Credit>();
        ArrayList<Balance> balances = new ArrayList<Balance>();
        ArrayList<Debit> unknownDebits = new ArrayList<Debit>();
        HashMap<DebitKnowledgeItem, ArrayList<Debit>> knownDebits = new HashMap<DebitKnowledgeItem, ArrayList<Debit>>();

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
                                ArrayList<Debit> debits = new ArrayList<Debit>();
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

    private static void appendBalances(StringBuilder output, ArrayList<Balance> balances) {
        if (balances.isEmpty()) return;

        output.append(SEPARATOR);
        output.append(System.lineSeparator());
        output.append("BALANCES");
        output.append(System.lineSeparator());
        for (Balance balance : balances) {
            output.append(balance.getOriginalText());
            output.append(System.lineSeparator());
        }
    }

    private static void appendCredits(StringBuilder output, ArrayList<Credit> credits) {
        if (credits.isEmpty()) return;

        output.append(SEPARATOR);
        output.append(System.lineSeparator());
        output.append("CREDITS");
        output.append(System.lineSeparator());
        for (Credit credit : credits) {
            output.append(credit.getOriginalText());
            output.append(System.lineSeparator());
        }
    }

    private static void appendUnknownDebits(StringBuilder output, ArrayList<Debit> unknownDebits) {
        if (unknownDebits.isEmpty()) return;

        output.append(SEPARATOR);
        output.append(System.lineSeparator());
        output.append("UNKNOWN DEBITS");
        output.append(System.lineSeparator());
        for (Debit unknownDebit : unknownDebits) {
            output.append(unknownDebit.getOriginalText());
            output.append(System.lineSeparator());
        }
        output.append(System.lineSeparator());
        for (int i = 0; i < unknownDebits.size(); i++) {
            Debit unknownDebit = unknownDebits.get(i);
            output.append(unknownDebit.getValue());
            if (i != unknownDebits.size() - 1) {
                output.append("+");
            }
        }
        output.append(System.lineSeparator());
    }

    private static void appendKnownDebits(StringBuilder output, HashMap<DebitKnowledgeItem, ArrayList<Debit>> knownDebits) {
        knownDebits.forEach((k, v) -> {
            output.append(SEPARATOR);
            output.append(System.lineSeparator());
            output.append(k.getName());
            output.append(System.lineSeparator());
            for (Debit debit : v) {
                output.append(debit.getOriginalText());
                output.append(System.lineSeparator());
            }
            output.append(System.lineSeparator());
            for (int i = 0; i < v.size(); i++) {
                Debit debit = v.get(i);
                output.append(debit.getValue());
                if (i != v.size() - 1) {
                    output.append("+");
                }
            }
            output.append(System.lineSeparator());
        });
    }

}
