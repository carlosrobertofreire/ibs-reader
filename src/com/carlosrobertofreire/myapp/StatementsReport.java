package com.carlosrobertofreire.myapp;

import com.carlosrobertofreire.ibsreader.Debit;
import com.carlosrobertofreire.ibsreader.Extract;
import com.carlosrobertofreire.ibsreader.KnowledgeBase;
import com.carlosrobertofreire.ibsreader.KnowledgeItem;
import com.carlosrobertofreire.ibsreader.Statement;

import java.util.ArrayList;
import java.util.HashMap;

public class StatementsReport {

    public static final String SEPARATOR = "------------------------------------------------------------------------";

    public static void main(String[] args) {
        System.out.println(SEPARATOR);
        ArrayList<Statement> statements = Extract.getStatements();

        HashMap<String, ArrayList<Statement>> analysisResult = KnowledgeBase.analyze(statements);

        printData(analysisResult);
    }

    private static void printData(HashMap<String, ArrayList<Statement>> analysisResult) {
        analysisResult.forEach((k, v) -> {
            System.out.println(SEPARATOR);
            System.out.println(k);
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
