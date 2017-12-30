package com.carlosrobertofreire.ibsreader;

public class Main {

    public static void main(String[] args) {
        String[] statementStrings = new String[11];
        statementStrings[0] = "01/12\t\t\tRSHOP-KONI ARCO V-01/12       \t\t30,90\t-";
        statementStrings[1] = "01/12\t\t\tRSHOP-FETRANSPOR -01/12       \t\t20,00\t-";
        statementStrings[2] = "01/12\t\t\tRSHOP-SORVETE IT-01/12       \t\t3,50\t-";
        statementStrings[3] = "04/12\t\t\tRSHOP-COPACABANA -04/12       \t\t39,60\t-";
        statementStrings[4] = "04/12\t\t\tRSHOP-PAG*Magazin-04/12       \t\t53,50\t-";
        statementStrings[5] = "01/12\t\t\tSAQUE 24H 06166946       \t5086\t20,00\t-";
        statementStrings[6] = "01/12\t\t\tTBI 7041.21944-0/500       \t\t1.000,00";
        statementStrings[7] = "04/12\t\t\tTBI 7041.21944-0/500       \t\t1.000,00\t-";
        statementStrings[8] = "01/12\t\t\tIOF       \t\t0,57\t-\t\t";
        statementStrings[9] = "01/12\t\t\tSDO CTA/APL AUTOMATICAS       \t\t\t\t1.000,00";
        statementStrings[10] = "15/12\t\t\tSALDO FINAL DISPONIVEL       \t\t\t\t1.000,00\t";

        String separator = "++++++++++++++++++++++++++++++++";
        for (String statementString : statementStrings){
            System.out.println(separator);
            Statement statement = Main.convert(statementString);
            System.out.println(statement.toString());
        }
    }

    private static Statement convert(String statementString){
        String[] statementParts = statementString.split("\t");
        if (statementParts.length >= 8){
            return new Statement(statementParts[0], statementParts[7], "Balance");
        }
        String date = statementParts[0];
        String store = statementParts[3];
        String value = statementParts[5];
        String type = statementParts.length > 6 ? "Debit":"Credit";
        return new Statement(date, store, value, type);
    }
}
