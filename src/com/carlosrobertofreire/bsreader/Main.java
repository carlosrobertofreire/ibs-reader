package com.carlosrobertofreire.bsreader;

public class Main {

    public static void main(String[] args) {
        String separator = "++++++++++++++++++++++++++++++++";

        String statement = "01/12\t\t\tRSHOP-KONI ARCO V-01/12       \t\t30,90\t-";
        System.out.println("Original Statement");
        System.out.println(statement);

        System.out.println(separator);
        System.out.println("Statement Parts");
        String[] statementParts = statement.split("\t");
        for (int i = 0; i < statementParts.length; i++){
            System.out.println(i + ": " + statementParts[i]);
        }

        System.out.println(separator);
        System.out.println("Statement Processed");
        String date = statementParts[0];
        String store = statementParts[3];
        String value = statementParts[5];
        String type = statementParts[6].equals("-")?"Debit":"Credit";
        System.out.println("Date: " + date);
        System.out.println("Store: " + store.trim());
        System.out.println("Value: " + value);
        System.out.println("Type: " + type);
    }
}
