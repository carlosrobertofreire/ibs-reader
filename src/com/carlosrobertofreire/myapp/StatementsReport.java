package com.carlosrobertofreire.myapp;

import com.carlosrobertofreire.ibsreader.Balance;
import com.carlosrobertofreire.ibsreader.Credit;
import com.carlosrobertofreire.ibsreader.Debit;
import com.carlosrobertofreire.ibsreader.DebitKnowledgeItem;
import com.carlosrobertofreire.ibsreader.Extract;
import com.carlosrobertofreire.ibsreader.DebitKnowledgeBase;
import com.carlosrobertofreire.ibsreader.Statement;

import javax.swing.plaf.nimbus.State;
import java.util.ArrayList;
import java.util.HashMap;

public class StatementsReport {

    public static final String SEPARATOR = "------------------------------------------------------------------------";

    public static void main(String[] args) {
        System.out.println(SEPARATOR);
        ArrayList<Statement> statements = Extract.getStatements();


        ArrayList<Credit> credits = new ArrayList<Credit>();
        ArrayList<Balance> balances = new ArrayList<Balance>();

        HashMap<DebitKnowledgeItem, ArrayList<Debit>> knownDebits = new HashMap<DebitKnowledgeItem, ArrayList<Debit>>();
        ArrayList<Debit> unknownDebits = new ArrayList<Debit>();
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
                            if (knownDebits.containsKey(debitKnowledgeItem.getName())){
                                knownDebits.get(debitKnowledgeItem.getName()).add(debit);
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
            }
        }

        printKnownDebits(knownDebits);
        printUnknownDebits(unknownDebits);
    }

    private static void printUnknownDebits(ArrayList<Debit> unknownDebits) {
        if (unknownDebits.isEmpty()) return;

        System.out.println(SEPARATOR);
        System.out.println("UNKNOWN DEBITS");
        for (Debit unknownDebit : unknownDebits){
            System.out.println(unknownDebit.getOriginalText());
        }
        System.out.println(SEPARATOR);
    }

    private static void printKnownDebits(HashMap<DebitKnowledgeItem, ArrayList<Debit>> analysisResult) {
        analysisResult.forEach((k, v) -> {
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
            System.out.println(SEPARATOR);
        });
    }



}
