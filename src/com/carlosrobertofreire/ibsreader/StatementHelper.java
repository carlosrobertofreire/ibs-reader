package com.carlosrobertofreire.ibsreader;

public class StatementHelper {

    public static Statement convertStringToStatement(String statementString) throws InvalidStatementException {
        if (statementString == null || statementString.isEmpty())
            throw new InvalidStatementException("There is no statement value to convert", statementString);

        String[] parts = statementString.split("\t");
        if (parts.length < 6)
            throw new InvalidStatementException("Incorrect numbers of fields", statementString);

        if (parts.length >= 8 && StatementHelper.isNotHeader(statementString))
            return new Balance(parts[0], parts[7], statementString);

        String date = parts[0];
        String details = parts[3].trim();
        String value = parts[5];

        if (parts.length > 6)
            return new Debit(date, details, value, statementString);
        else
            return new Credit(date, details, value, statementString);
    }

    public static boolean isNotHeader(String statementString){
        String header = "Data\t \t \tLançamento\tAg/Origem\tValor (R$)\t \tSaldo (R$)\t ";
        return !header.toUpperCase().trim().equals(statementString.toUpperCase().trim());
    }

}
