package com.carlosrobertofreire.ibsreader;

import java.util.ArrayList;

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

    public static ArrayList<KnowledgeItem> getKnowledgeItems() {
        return knowledgeItems;
    }
}
