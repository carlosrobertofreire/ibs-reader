package com.github.carlosrvff.bsreader.converter;

import com.github.carlosrvff.bsreader.converter.converter.StatementHelper;
import com.github.carlosrvff.bsreader.converter.domain.Balance;
import com.github.carlosrvff.bsreader.converter.domain.Credit;
import com.github.carlosrvff.bsreader.converter.exception.InvalidStatementException;
import com.github.carlosrvff.bsreader.converter.domain.Debit;
import com.github.carlosrvff.bsreader.converter.domain.Statement;
import org.junit.Test;

import static org.junit.Assert.*;

public class StatementHelperTest {

    @Test
    public void convertToDebitStatements() {
        String[] texts = {
                "01/12\t\t\tRSHOP-KONI ARCO V-01/12       \t\t30,00\t-",
                "01/12\t\t\tSAQUE 24H 06166946       \t5086\t20,00\t-",
                "01/12\t\t\tIOF       \t\t0,57\t-\t\t",
                "04/12\t\t\tCXE 001305 SAQUE       \t8410\t100,00\t-\t\t",
                "13/12\t\t\tINT TED 140000101612830       \t\t250,00\t-"
        };
        try {
            for (String text : texts){
                assertTrue(StatementHelper.convertStringToStatement(text) instanceof Debit);
            }
        } catch (InvalidStatementException e) {
            fail();
        }
    }

    @Test
    public void convertToCreditStatements() {
        String[] texts = {
                "01/12\t\t\tTBI 3333.22222-0/500       \t\t1.000,00",
                "05/12\t C\t\tDOC 104.0070MARIA R SA        \t\t100,00\t\t",
                "18/12\t\t\tCEI 000015 DINHEIRO       \t719\t200,00\t\t\t",
                "18/12\t\t\tREND PAGO APLIC AUT MAIS       \t\t0,01\t\t\t",
                "05/12\t\t\tREMUNERACAO/SALARIO       \t3032\t1.000,00\t\t\t"
        };
        try {
            for (String text : texts){
                assertTrue(StatementHelper.convertStringToStatement(text) instanceof Credit);
            }
        } catch (InvalidStatementException e) {
            fail();
        }
    }

    @Test
    public void convertToBalanceStatements() {
        String[] texts = {
                "01/12\t\t\tSDO CTA/APL AUTOMATICAS       \t\t\t\t1.000,00",
                "15/12\t\t\tSALDO FINAL DISPONIVEL       \t\t\t\t1.000,00\t",
                "26/12\t\t\tSALDO DO DIA       \t\t\t\t500,00\t-",
                "15/12\t\t\t(-) SALDO A LIBERAR       \t\t\t\t500,00\t"
        };
        try {
            for (String text : texts){
                assertTrue(StatementHelper.convertStringToStatement(text) instanceof Balance);
            }
        } catch (InvalidStatementException e) {
            fail();
        }
    }

    @Test
    public void convertStringToDebitStatement() {
        String debitExample = "01/12\t\t\tRSHOP-KONI ARCO V-01/12       \t\t30,00\t-";
        try {
            Statement statement = StatementHelper.convertStringToStatement(debitExample);
            assertTrue(statement instanceof Debit);
            Debit debit = (Debit) statement;
            assertEquals("01/12",debit.getDate());
            assertEquals("RSHOP-KONI ARCO V-01/12",debit.getStore());
            assertEquals("30,00",debit.getValue());
        } catch (InvalidStatementException e) {
            fail();
        }
    }

    @Test
    public void convertStringToBalanceStatement() {
        String text = "15/12\t\t\tSALDO FINAL DISPONIVEL       \t\t\t\t1.000,00\t";
        try {
            Statement statement = StatementHelper.convertStringToStatement(text);
            assertTrue(statement instanceof Balance);
            Balance balance = (Balance) statement;
            assertEquals("15/12",balance.getDate());
            assertEquals("1.000,00",balance.getValue());
        } catch (InvalidStatementException e) {
            fail();
        }
    }

    @Test
    public void convertStringToCreditStatement() {
        String text = "05/12\t\t\tREMUNERACAO/SALARIO       \t3032\t1.000,00\t\t\t";
        try {
            Statement statement = StatementHelper.convertStringToStatement(text);
            assertTrue(statement instanceof Credit);
            Credit credit = (Credit) statement;
            assertEquals("05/12",credit.getDate());
            assertEquals("REMUNERACAO/SALARIO",credit.getFrom());
            assertEquals("1.000,00",credit.getValue());
        } catch (InvalidStatementException e) {
            fail();
        }
    }

    @Test
    public void convertInvalidStringToStatement() {
        String text = "adsadas\t";
        try {
            Statement statement = StatementHelper.convertStringToStatement(text);
        } catch (Exception e) {
            assertTrue(e instanceof InvalidStatementException);
        }
    }

    @Test
    public void convertEmptyStringToStatement() {
        String text = "";
        try {
            Statement statement = StatementHelper.convertStringToStatement(text);
        } catch (Exception e) {
            assertTrue(e instanceof InvalidStatementException);
        }
    }

    @Test
    public void convertNullToStatement() {
        try {
            Statement statement = StatementHelper.convertStringToStatement(null);
        } catch (Exception e) {
            assertTrue(e instanceof InvalidStatementException);
        }
    }
}