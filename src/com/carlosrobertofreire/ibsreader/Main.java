package com.carlosrobertofreire.ibsreader;

public class Main {

    public static void main(String[] args) {
        String[] statementStrings = {
                "01/12\t\t\tRSHOP-KONI ARCO V-01/12       \t\t30,90\t-",
                "01/12\t\t\tRSHOP-FETRANSPOR -01/12       \t\t20,00\t-",
                "01/12\t\t\tRSHOP-SORVETE IT-01/12       \t\t3,50\t-",
                "04/12\t\t\tRSHOP-COPACABANA -04/12       \t\t39,60\t-",
                "04/12\t\t\tRSHOP-PAG*Magazin-04/12       \t\t53,50\t-",
                "01/12\t\t\tSAQUE 24H 06166946       \t5086\t20,00\t-",
                "01/12\t\t\tTBI 7041.21944-0/500       \t\t1.000,00",
                "04/12\t\t\tTBI 7041.21944-0/500       \t\t1.000,00\t-",
                "01/12\t\t\tIOF       \t\t0,57\t-\t\t",
                "01/12\t\t\tSDO CTA/APL AUTOMATICAS       \t\t\t\t1.000,00",
                "15/12\t\t\tSALDO FINAL DISPONIVEL       \t\t\t\t1.000,00\t"
        };

        for (String statementString : statementStrings){
            Statement statement = Main.convert(statementString);
            System.out.println(statement.toString());
        }
    }

    private static Statement convert(String statementString){
        String[] parts = statementString.split("\t");
        if (parts.length >= 8){
            return new Statement(parts[0], parts[7], StatementType.Balance);
        }
        String date = parts[0];
        String store = parts[3];
        String value = parts[5];
        StatementType type = parts.length > 6 ? StatementType.Debit:StatementType.Credit;
        return new Statement(date, store, value, type);
    }
}
