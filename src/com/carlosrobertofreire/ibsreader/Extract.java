package com.carlosrobertofreire.ibsreader;

import com.carlosrobertofreire.myapp.StatementsReport;

import java.util.ArrayList;

public class Extract {

    private static Statement[] statements;

    static {
        String[] statementStrings = {
                "01/12\t\t\tRSHOP-KONI ARCO V-01/12       \t\t30,00\t-",
                "01/12\t\t\tSAQUE 24H 06166946       \t5086\t20,00\t-",
                "01/12\t\t\tTBI 3333.22222-0/500       \t\t1.000,00",
                "01/12\t\t\tIOF       \t\t0,57\t-\t\t",
                "01/12\t\t\tSDO CTA/APL AUTOMATICAS       \t\t\t\t1.000,00",
                "15/12\t\t\tSALDO FINAL DISPONIVEL       \t\t\t\t1.000,00\t",
                "26/12\t\t\tSALDO DO DIA       \t\t\t\t500,00\t-",
                "15/12\t\t\t(-) SALDO A LIBERAR       \t\t\t\t500,00\t",
                "04/12\t\t\tCXE 001305 SAQUE       \t8410\t100,00\t-\t\t",
                "05/12\t C\t\tDOC 104.0070MARIA R SA        \t\t100,00\t\t",
                "18/12\t\t\tCEI 000015 DINHEIRO       \t719\t200,00\t\t\t",
                "18/12\t\t\tREND PAGO APLIC AUT MAIS       \t\t0,01\t\t\t",
                "13/12\t\t\tINT TED 140000101612830       \t\t250,00\t-",
                "05/12\t\t\tREMUNERACAO/SALARIO       \t3032\t1.000,00\t\t\t",
                "adsadas\t",
                "dsanldnsa wqkndlaksndlkas"
        };
        ArrayList<Statement> statementsArrayList = new ArrayList<Statement>();
        for (String statementString : statementStrings){
            try {
                Statement statement = StatementHelper.convertStringToStatement(statementString);
                statementsArrayList.add(statement);
            } catch (InvalidStatementException e){
                System.out.println(e.getMessage());
                if (e.getCause() != null)
                    System.out.println("  Original exception: " + e.getCause().getMessage());
                System.out.println(StatementsReport.SEPARATOR);
            }
        }
        statements = statementsArrayList.toArray(new Statement[14]);
    }

    public static Statement[] getStatements() {
        return statements;
    }
}
