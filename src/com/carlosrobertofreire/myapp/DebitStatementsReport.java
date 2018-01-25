package com.carlosrobertofreire.myapp;

import com.carlosrobertofreire.ibsreader.Debit;
import com.carlosrobertofreire.ibsreader.Extract;
import com.carlosrobertofreire.ibsreader.KnowledgeBase;
import com.carlosrobertofreire.ibsreader.KnowledgeItem;
import com.carlosrobertofreire.ibsreader.Statement;

import java.util.ArrayList;

public class DebitStatementsReport {

    private static final String SEPARATOR = "------------------------------------------------------------------------";

    public static void main(String[] args) {
        Statement[] statements = Extract.getStatements();

        ArrayList<KnowledgeItem> knowledgeItems = KnowledgeBase.getKnowledgeItems();

        processData(statements, knowledgeItems);

        printData(knowledgeItems);
    }

    private static void processData(Statement[] statements, ArrayList<KnowledgeItem> knowledgeItems) {
        for (Statement statement : statements){
            if (statement instanceof Debit){
                Debit debit = (Debit) statement;
                boolean found = false;
                for (int i = 0; i < knowledgeItems.size() && !found; i++){
                    KnowledgeItem knowledgeItem = knowledgeItems.get(i);
                    for (String keyword : knowledgeItem.getKeywords()){
                        if (debit.getStore().toUpperCase().contains(keyword.toUpperCase())){
                            found = true;
                            knowledgeItem.addStatement(debit);
                            break;
                        }
                    }
                }
            }
        }
    }

    private static void printData(ArrayList<KnowledgeItem> knowledgeItems) {
        for (int i = 0; i < knowledgeItems.size(); i++){
            if (i == 0) System.out.println(SEPARATOR);
            System.out.println(knowledgeItems.get(i));
            System.out.println(SEPARATOR);
        }
    }

}
