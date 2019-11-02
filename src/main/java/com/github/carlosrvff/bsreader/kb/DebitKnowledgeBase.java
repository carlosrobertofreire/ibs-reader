package com.github.carlosrvff.bsreader.kb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class DebitKnowledgeBase {

    private static ArrayList<DebitKnowledgeItem> debitKnowledgeItems;

    static {
        loadDebitKnowledegeItems();
    }

    private static void loadDebitKnowledegeItems() {
        debitKnowledgeItems = new ArrayList<DebitKnowledgeItem>();

        String userHome = System.getProperty("user.home");
        String fileName = userHome + "/IBSReader/debit-kb.txt";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            HashMap<String, ArrayList<String>> contentFileHash = new HashMap<String, ArrayList<String>>();
            String currentKey = "";
            while ((line = bufferedReader.readLine()) != null) {
                if (isNewDebitKnowledgeItem(line)){
                    currentKey = line;
                    contentFileHash.put(line, new ArrayList<String>());
                } else {
                    if (!line.isEmpty() && contentFileHash.containsKey(currentKey)){
                        contentFileHash.get(currentKey).add(line);
                    }
                }
            }
            contentFileHash.forEach((k, v) -> {
                DebitKnowledgeItem item = new DebitKnowledgeItem();
                item.setName(k);
                item.setKeywords(v);
                debitKnowledgeItems.add(item);
            });
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static boolean isNewDebitKnowledgeItem(String line) {
        return line != null && line.startsWith("#");
    }

    public static ArrayList<DebitKnowledgeItem> getDebitKnowledgeItems(){
        return debitKnowledgeItems;
    }

}
