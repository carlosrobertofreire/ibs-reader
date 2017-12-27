package com.carlosrobertofreire.bsreader;

public class Main {

    public static void main(String[] args) {
        String separator = "++++++++++++++++++++++++++++++++";

        String[] statements = new String[11];
        statements[0] = "01/12\t\t\tRSHOP-KONI ARCO V-01/12       \t\t30,90\t-";
        statements[1] = "01/12\t\t\tRSHOP-FETRANSPOR -01/12       \t\t20,00\t-";
        statements[2] = "01/12\t\t\tRSHOP-SORVETE IT-01/12       \t\t3,50\t-";
        statements[3] = "04/12\t\t\tRSHOP-COPACABANA -04/12       \t\t39,60\t-";
        statements[4] = "04/12\t\t\tRSHOP-PAG*Magazin-04/12       \t\t53,50\t-";
        statements[5] = "01/12\t\t\tSAQUE 24H 06166946       \t5086\t20,00\t-";
        statements[6] = "01/12\t\t\tTBI 7041.21944-0/500       \t\t1.000,00";
        statements[7] = "04/12\t\t\tTBI 7041.21944-0/500       \t\t1.000,00\t-";
        statements[8] = "01/12\t\t\tIOF       \t\t0,57\t-\t\t";
        statements[9] = "01/12\t\t\tSDO CTA/APL AUTOMATICAS       \t\t\t\t1.000,00";
        statements[10] = "15/12\t\t\tSALDO FINAL DISPONIVEL       \t\t\t\t1.000,00\t";


        for (String statement : statements){
            System.out.println(separator);
            String[] statementParts = statement.split("\t");
            if (statementParts.length >= 8){
                System.out.println("SKIPPED: Balance");
                continue;
            }
            System.out.println("Statement Processed");
            String date = statementParts[0];
            String store = statementParts[3];
            String value = statementParts[5];
            String type = statementParts.length > 6 ? "Debit":"Credit";
            System.out.println("Date: " + date);
            System.out.println("Store: " + store.trim());
            System.out.println("Value: " + value);
            System.out.println("Type: " + type);
        }

        System.out.println(separator);
        String anotherStatement = "15/12\t\t\tSALDO FINAL DISPONIVEL       \t\t\t\t1.000,00\t";
        System.out.println("Another Statement: " + anotherStatement);
        String[] statementParts = anotherStatement.split("\t");
        for (int i = 0; i < statementParts.length; i++){
            System.out.println(i + ": " + statementParts[i]);
        }

    }
}
