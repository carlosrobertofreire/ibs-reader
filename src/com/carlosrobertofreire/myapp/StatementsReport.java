package com.carlosrobertofreire.myapp;

import com.carlosrobertofreire.ibsreader.Balance;
import com.carlosrobertofreire.ibsreader.Credit;
import com.carlosrobertofreire.ibsreader.Debit;
import com.carlosrobertofreire.ibsreader.DebitKnowledgeBase;
import com.carlosrobertofreire.ibsreader.DebitKnowledgeItem;
import com.carlosrobertofreire.ibsreader.Extract;
import com.carlosrobertofreire.ibsreader.Statement;

import java.util.ArrayList;
import java.util.HashMap;

public class StatementsReport {

    public static final String SEPARATOR = "------------------------------------------------------------------------";

    public static void main(String[] args) {
        System.out.println("Loading statements...");
        System.out.println(SEPARATOR);
        ArrayList<Statement> statements = Extract.getStatements();
        System.out.println(SEPARATOR);
        System.out.println("Processing data...");
        ArrayList<Credit> credits = new ArrayList<Credit>();
        ArrayList<Balance> balances = new ArrayList<Balance>();
        ArrayList<Debit> unknownDebits = new ArrayList<Debit>();
        HashMap<DebitKnowledgeItem, ArrayList<Debit>> knownDebits = new HashMap<DebitKnowledgeItem, ArrayList<Debit>>();

        ArrayList<DebitKnowledgeItem> debitKnowledgeItems = DebitKnowledgeBase.getDebitKnowledgeItems();
        for (Statement statement : statements){
            if (statement instanceof Debit){
                Debit debit = (Debit) statement;
                boolean found = false;
                for (int i = 0; i < debitKnowledgeItems.size() && !found; i++){
                    DebitKnowledgeItem debitKnowledgeItem = debitKnowledgeItems.get(i);
                    for (String keyword : debitKnowledgeItem.getKeywords()){
                        if (debit.getStore().toUpperCase().contains(keyword.toUpperCase())){
                            found = true;
                            if (knownDebits.containsKey(debitKnowledgeItem)){
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
                if (!found){
                    unknownDebits.add(debit);
                }
            } else if (statement instanceof Credit){
                credits.add( (Credit) statement);
            } else if (statement instanceof Balance) {
                balances.add((Balance) statement);
            }
        }

        printKnownDebits(knownDebits);
        printUnknownDebits(unknownDebits);
        printCredits(credits);
        printBalances(balances);
    }

    private static void printBalances(ArrayList<Balance> balances) {
        if (balances.isEmpty()) return;

        System.out.println(SEPARATOR);
        System.out.println("BALANCES");
        for (Balance balance : balances){
            System.out.println(balance.getOriginalText());
        }
    }

    private static void printCredits(ArrayList<Credit> credits) {
        if (credits.isEmpty()) return;

        System.out.println(SEPARATOR);
        System.out.println("CREDITS");
        for (Credit credit : credits){
            System.out.println(credit.getOriginalText());
        }
    }

    private static void printUnknownDebits(ArrayList<Debit> unknownDebits) {
        if (unknownDebits.isEmpty()) return;

        System.out.println(SEPARATOR);
        System.out.println("UNKNOWN DEBITS");
        for (Debit unknownDebit : unknownDebits){
            System.out.println(unknownDebit.getOriginalText());
        }
    }

    private static void printKnownDebits(HashMap<DebitKnowledgeItem, ArrayList<Debit>> knownDebits) {
        knownDebits.forEach((k, v) -> {
            System.out.println(SEPARATOR);
            System.out.println(k.getName());
            for (Statement statement : v){
                System.out.println(statement.getOriginalText());
            }
            System.out.println();
            for (int i = 0; i < v.size(); i++){
                Statement statement = v.get(i);
                System.out.print(statement.getValue());
                if (i != v.size() - 1){
                    System.out.print(" + ");
                }
            }
            System.out.println();
        });
    }

}
