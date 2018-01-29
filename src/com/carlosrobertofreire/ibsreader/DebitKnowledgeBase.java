package com.carlosrobertofreire.ibsreader;

import java.util.ArrayList;
import java.util.HashMap;

public class DebitKnowledgeBase {

    private static ArrayList<DebitKnowledgeItem> debitKnowledgeItems;

    static {
        //Create sample data for debitKnowledgeItems
        DebitKnowledgeItem food = new DebitKnowledgeItem();
        food.setName("Food");
        food.setKeywords(new String[]{"KONI", "SAQUE"});

        DebitKnowledgeItem tax = new DebitKnowledgeItem();
        tax.setName("Tax");
        tax.setKeywords(new String[]{"IOF"});

        debitKnowledgeItems = new ArrayList<DebitKnowledgeItem>();
        debitKnowledgeItems.add(food);
        debitKnowledgeItems.add(tax);

    }

    public static ArrayList<DebitKnowledgeItem> getDebitKnowledgeItems(){
        return debitKnowledgeItems;
    }

}
