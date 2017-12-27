package com.carlosrobertofreire.bsreader;

public class Main {

    public static void main(String[] args) {
        String separator = "++++++++++++++++++++++++++++++++";

        String[] statements = new String[5];
        statements[0] = "01/12\t\t\tRSHOP-KONI ARCO V-01/12       \t\t30,90\t-";
        statements[1] = "01/12\t\t\tRSHOP-FETRANSPOR -01/12       \t\t20,00\t-";
        statements[2] = "01/12\t\t\tRSHOP-SORVETE IT-01/12       \t\t3,50\t-";
        statements[3] = "04/12\t\t\tRSHOP-COPACABANA -04/12       \t\t39,60\t-";
        statements[4] = "04/12\t\t\tRSHOP-PAG*Magazin-04/12       \t\t53,50\t-";

        for (String statement : statements){
            System.out.println(separator);
            System.out.println("Statement Processed");
            String[] statementParts = statement.split("\t");
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
}
