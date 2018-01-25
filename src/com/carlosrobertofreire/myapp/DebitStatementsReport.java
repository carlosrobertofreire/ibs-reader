package com.carlosrobertofreire.myapp;

import com.carlosrobertofreire.ibsreader.Debit;
import com.carlosrobertofreire.ibsreader.Extract;
import com.carlosrobertofreire.ibsreader.KnowledgeBase;
import com.carlosrobertofreire.ibsreader.KnowledgeItem;
import com.carlosrobertofreire.ibsreader.Statement;

public class DebitStatementsReport {

    public static void main(String[] args) {
        Statement[] statements = Extract.getStatements();

        KnowledgeBase knowledgeBase = new KnowledgeBase();

        processData(statements, knowledgeBase);

        printData(knowledgeBase);
    }

    private static void processData(Statement[] statements, KnowledgeBase knowledgeBase) {
        for (Statement statement : statements){
            if (statement instanceof Debit){
                Debit debit = (Debit) statement;
                boolean found = false;
                for (int i = 0; i < knowledgeBase.getItems().length && !found; i++){
                    KnowledgeItem knowledgeItem = knowledgeBase.getItems()[i];
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

    private static void printData(KnowledgeBase knowledgeBase) {
        for (KnowledgeItem knowledgeItem : knowledgeBase.getItems()){
            System.out.println(knowledgeItem.getName());
            for (Statement statement : knowledgeItem.getStatements()){
                System.out.println(statement);
            }
            System.out.println();
            for (int i = 0; i < knowledgeItem.getStatements().size(); i++){
                Statement statement = knowledgeItem.getStatements().get(i);
                System.out.print(statement.getValue());
                if (i != knowledgeItem.getStatements().size() - 1){
                    System.out.print(" + ");
                }
            }
            System.out.println();
        }
    }

}
