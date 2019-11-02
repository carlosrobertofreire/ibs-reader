package com.github.carlosrvff.bsreader.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.github.carlosrvff.bsreader.converter.converter.ItauConverter;
import com.github.carlosrvff.bsreader.converter.domain.Balance;
import com.github.carlosrvff.bsreader.converter.domain.Credit;
import com.github.carlosrvff.bsreader.converter.domain.Debit;
import com.github.carlosrvff.bsreader.converter.domain.Statement;
import com.github.carlosrvff.bsreader.converter.exception.InvalidStatementException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ItauConverterTest {

  private ItauConverter target;

  @Before
  public void setUp() throws Exception {
    target = new ItauConverter();
  }

  @Test
  public void convertTextToDebitStatement() throws InvalidStatementException {
    String textFixture = "01/12\t\t\tRSHOP-KONI ARCO V-01/12       \t\t30,00\t-";

    Statement statement = target.toStatement(textFixture);

    Assert.assertTrue(statement instanceof Debit);

    Debit debit = (Debit) statement;
    Assert.assertEquals("01/12", debit.getDate());
    Assert.assertEquals("RSHOP-KONI ARCO V-01/12", debit.getStore());
    Assert.assertEquals("30,00", debit.getValue());
  }

  @Test
  public void convertTextToCreditStatement() throws InvalidStatementException {
    String textFixture = "05/12\t\t\tREMUNERACAO/SALARIO       \t3032\t1.000,00\t\t\t";

    Statement statement = target.toStatement(textFixture);

    Assert.assertTrue(statement instanceof Credit);
    Credit credit = (Credit) statement;
    Assert.assertEquals("05/12", credit.getDate());
    Assert.assertEquals("REMUNERACAO/SALARIO", credit.getFrom());
    Assert.assertEquals("1.000,00", credit.getValue());
  }

  @Test
  public void convertTextToBalanceStatement() throws InvalidStatementException {
    String textFixture = "15/12\t\t\tSALDO FINAL DISPONIVEL       \t\t\t\t1.000,00\t";

    Statement statement = target.toStatement(textFixture);

    Assert.assertTrue(statement instanceof Balance);
    Balance balance = (Balance) statement;
    Assert.assertEquals("15/12", balance.getDate());
    Assert.assertEquals("1.000,00", balance.getValue());
  }

  @Test
  public void convertTextToStatementWhenTextIsValid() {
    String textFixture = "adsadas\t";
    try {
      Statement statement = target.toStatement(textFixture);
    } catch (Exception e) {
      assertTrue(e instanceof InvalidStatementException);
    }
  }

  @Test
  public void convertTextToStatementWhenTextIsEmpty() {
    String textFixture = "";
    try {
      Statement statement = target.toStatement(textFixture);
    } catch (Exception e) {
      assertTrue(e instanceof InvalidStatementException);
    }
  }

  @Test
  public void convertTextToStatementWhenTextIsNull() {
    try {
      Statement statement = target.toStatement(null);
    } catch (Exception e) {
      assertTrue(e instanceof InvalidStatementException);
    }
  }
}