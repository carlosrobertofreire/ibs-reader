package com.carlosrvff.bsreader.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.carlosrvff.bsreader.domain.Statement;
import com.carlosrvff.bsreader.helper.BankStatementUtils;
import com.carlosrvff.bsreader.helper.KbcStatementUtils;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KbcConverterTest extends BankConverterTest {

  private KbcConverter target;
  private KbcStatementUtils utils;

  @BeforeEach
  protected void setUp() {
    super.setUp();
    target = (KbcConverter) getBankConverter();
    utils = (KbcStatementUtils) getBankStatementUtils();
  }

  @Override
  protected BankConverter getBankConverter() {
    return new KbcConverter();
  }

  @Override
  protected BankStatementUtils getBankStatementUtils() {
    return new KbcStatementUtils();
  }

  @Test
  void toStatementsWhenTextHasTwoStatements() {
    String twoStatementsText = utils.createTwoStatementsText();

    List<Statement> statements =
        target.toStatements(twoStatementsText.split(System.lineSeparator()));

    assertNotNull(statements);
    assertEquals(2, statements.size());
  }

  @Test
  void toStatementsWhenTextIsInvalid() {
    List<Statement> statements = target.toStatements(new String[] {"Invalid"});

    assertNotNull(statements);
    assertTrue(statements.isEmpty());
  }
}
