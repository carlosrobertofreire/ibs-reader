package com.carlosrobertofreire.ibsreader;

import java.util.ArrayList;
import java.util.HashMap;

public class KnowledgeBase {

    private static ArrayList<KnowledgeItem> knowledgeItems;

    static {
        //Create sample data for knowledgeItems
        KnowledgeItem food = new KnowledgeItem();
        food.setName("Food");
        food.setKeywords(new String[]{"KONI", "SAQUE"});

        KnowledgeItem tax = new KnowledgeItem();
        tax.setName("Tax");
        tax.setKeywords(new String[]{"IOF"});

        knowledgeItems = new ArrayList<KnowledgeItem>();
        knowledgeItems.add(food);
        knowledgeItems.add(tax);

    }

    public static HashMap<KnowledgeItem, ArrayList<Statement>> analyze(ArrayList<Statement> statements){
        HashMap<KnowledgeItem, ArrayList<Statement>> hashMap = new HashMap<KnowledgeItem, ArrayList<Statement>>();

        for (Statement statement : statements){
            if (statement instanceof Debit){
                Debit debit = (Debit) statement;
                boolean found = false;
                for (int i = 0; i < knowledgeItems.size() && !found; i++){
                    KnowledgeItem knowledgeItem = knowledgeItems.get(i);
                    for (String keyword : knowledgeItem.getKeywords()){
                        if (debit.getStore().toUpperCase().contains(keyword.toUpperCase())){
                            found = true;
                            if (hashMap.containsKey(knowledgeItem)){
                                hashMap.get(knowledgeItem).add(debit);
                            } else {
                                ArrayList<Statement> newStatementsList = new ArrayList<Statement>();
                                newStatementsList.add(debit);
                                hashMap.put(knowledgeItem, newStatementsList);
                            }
                            break;
                        }
                    }
                }
            } else {
                System.out.println(statement.getOriginalText());
            }
        }

        return hashMap;
    }
}
