package com.carlosrvff.bsreader.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.carlosrvff.bsreader.domain.Credit;
import com.carlosrvff.bsreader.domain.Debit;
import com.carlosrvff.bsreader.domain.Statement;
import com.carlosrvff.bsreader.exception.InvalidStatementException;
import com.carlosrvff.bsreader.helper.KbcStatementUtils;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KbcConverterTest {

  private KbcConverter target;
  private KbcStatementUtils utils;

  @BeforeEach
  void setUp() {
    target = new KbcConverter();
    utils = new KbcStatementUtils();
  }

  @Test
  void toStatementWhenTextIsDebit() throws InvalidStatementException {
    String debitText = utils.createDebitStatementText();
    Debit expectedDebit = utils.createDebitStatement(debitText);

    Statement statement = target.toStatement(debitText);

    assertTrue(statement instanceof Debit);
    assertEquals(expectedDebit, statement);
  }

  @Test
  void toStatementWhenTextIsCredit() throws InvalidStatementException {
    String creditText = utils.createCreditStatementText();
    Credit expectedCredit = utils.createCreditStatement(creditText);

    Statement statement = target.toStatement(creditText);

    assertTrue(statement instanceof Credit);
    assertEquals(expectedCredit, statement);
  }

  @Test
  void toStatementWhenTextIsEmpty() {
    String textFixture = "";

    assertThrows(InvalidStatementException.class, () -> target.toStatement(textFixture));
  }

  @Test
  void toStatementWhenTextIsInvalid() {
    String textFixture = "InvalidText";

    assertThrows(InvalidStatementException.class, () -> target.toStatement(textFixture));
  }

  @Test
  void toStatementWhenTextIsNull() {
    assertThrows(NullPointerException.class, () -> target.toStatement(null));
  }

  @Test
  void toStatementWhenTextIsHeader() {
    String textFixture = target.getHeader();

    assertThrows(InvalidStatementException.class, () -> target.toStatement(textFixture));
  }

  @Test
  void toStatementsWhenTextHasTwoStatements() throws InvalidStatementException {
    String twoStatementsText = utils.createTwoStatementsText();

    List<Statement> statements =
        target.toStatements(twoStatementsText.split(System.lineSeparator()));

    assertNotNull(statements);
    assertEquals(2, statements.size());
  }

  @Test
  void toStatementsWhenTextIsInvalid() throws InvalidStatementException {
    List<Statement> statements = target.toStatements(new String[] {"Invalid"});

    assertNotNull(statements);
    assertTrue(statements.isEmpty());
  }

  @Test
  void getHeader() {
    assertTrue(StringUtils.isNotBlank(target.getHeader()));
  }
}
