package com.carlosrobertofreire.ibsreader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class DebitKnowledgeBase {

    private static ArrayList<DebitKnowledgeItem> debitKnowledgeItems;

    static {
        String userHome = System.getProperty("user.home");
        String fileName = userHome + "/IBSReader/debit-kb.txt";

        debitKnowledgeItems = new ArrayList<DebitKnowledgeItem>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            HashMap<String, ArrayList<String>> fileHash = new HashMap<String, ArrayList<String>>();
            String currentKey = "";
            while ((line = bufferedReader.readLine()) != null) {
                if (DebitKnowledgeBase.isNewDebitKnowledgeItem(line)){
                    currentKey = line;
                    fileHash.put(line, new ArrayList<String>());
                } else {
                    if (!line.isEmpty() && fileHash.containsKey(currentKey)){
                        fileHash.get(currentKey).add(line);
                    }
                }
            }
            fileHash.forEach((k, v) -> {
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
