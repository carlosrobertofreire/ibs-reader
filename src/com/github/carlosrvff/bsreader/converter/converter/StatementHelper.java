package com.github.carlosrvff.bsreader.converter.converter;

import com.github.carlosrvff.bsreader.converter.domain.Balance;
import com.github.carlosrvff.bsreader.converter.exception.InvalidStatementException;
import com.github.carlosrvff.bsreader.converter.domain.Credit;
import com.github.carlosrvff.bsreader.converter.domain.Debit;
import com.github.carlosrvff.bsreader.converter.domain.Statement;

public class StatementHelper {

    public static Statement convertStringToStatement(String statementString) throws InvalidStatementException {
        if (statementString == null || statementString.isEmpty())
            throw new InvalidStatementException("There is no statement value to convert", statementString);

        if (StatementHelper.isHeader(statementString))
            throw new InvalidStatementException("Invalid statement", statementString);

        String[] parts = statementString.split("\t");
        if (parts.length < 6)
            throw new InvalidStatementException("Incorrect numbers of fields", statementString);

        if (parts.length >= 8)
            return new Balance(parts[0], parts[7], statementString);

        String date = parts[0];
        String details = parts[3].trim();
        String value = parts[5];

        if (parts.length > 6)
            return new Debit(date, details, value, statementString);
        else
            return new Credit(date, details, value, statementString);
    }

    public static boolean isHeader(String statementString){
        String header = "Data\t \t \tLançamento\tAg/Origem\tValor (R$)\t \tSaldo (R$)\t ";
        return header.toUpperCase().trim().equals(statementString.toUpperCase().trim());
    }

}