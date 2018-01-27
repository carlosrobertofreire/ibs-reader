package com.carlosrobertofreire.ibsreader;

import java.util.ArrayList;
import java.util.HashMap;

public class KnowledgeBase {

    private static ArrayList<KnowledgeItem> knowledgeItems;

    static {
        //Create sample data for knowledgeItems
        KnowledgeItem food = new KnowledgeItem();
        food.setName("Debit - Food");
        food.setKeywords(new String[]{"KONI", "SAQUE"});

        KnowledgeItem tax = new KnowledgeItem();
        tax.setName("Debit - Tax");
        tax.setKeywords(new String[]{"IOF"});

        knowledgeItems = new ArrayList<KnowledgeItem>();
        knowledgeItems.add(food);
        knowledgeItems.add(tax);

    }

    public static HashMap<String, ArrayList<Statement>> analyze(ArrayList<Statement> statements){
        HashMap<String, ArrayList<Statement>> hashMap = new HashMap<String, ArrayList<Statement>>();

        for (Statement statement : statements){
            if (statement instanceof Debit){
                processDebitStatement(hashMap, (Debit) statement);
            } else if (statement instanceof Credit){
                processCreditStatement(hashMap, (Credit) statement);
            }
        }

        return hashMap;
    }

    private static void processCreditStatement(HashMap<String, ArrayList<Statement>> hashMap, Credit statement) {
        String creditKey = "Credit Statements";
        Credit newCredit = statement;
        if (hashMap.containsKey(creditKey)){
            hashMap.get(creditKey).add(newCredit);
        } else {
            ArrayList<Statement> credits = new ArrayList<Statement>();
            credits.add(newCredit);
            hashMap.put(creditKey, credits);
        }
    }

    private static void processDebitStatement(HashMap<String, ArrayList<Statement>> hashMap, Debit statement) {
        Debit debit = statement;
        boolean found = false;
        for (int i = 0; i < knowledgeItems.size() && !found; i++){
            KnowledgeItem knowledgeItem = knowledgeItems.get(i);
            for (String keyword : knowledgeItem.getKeywords()){
                if (debit.getStore().toUpperCase().contains(keyword.toUpperCase())){
                    found = true;
                    if (hashMap.containsKey(knowledgeItem.getName())){
                        hashMap.get(knowledgeItem.getName()).add(debit);
                    } else {
                        ArrayList<Statement> newStatementsList = new ArrayList<Statement>();
                        newStatementsList.add(debit);
                        hashMap.put(knowledgeItem.getName(), newStatementsList);
                    }
                    break;
                }
            }

        }
    }
}
