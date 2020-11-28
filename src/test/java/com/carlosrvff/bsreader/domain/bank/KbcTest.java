package com.carlosrvff.bsreader.domain.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.carlosrvff.bsreader.domain.Statement;
import com.carlosrvff.bsreader.helper.KbcStatementUtils;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KbcTest {

  private Kbc target;

  @BeforeEach
  void setUp() {
    target = new Kbc();
  }

  @Test
  void getStatementsWhenTextHasTwoStatements() {
    String twoStatementsText = KbcStatementUtils.createTwoStatementsText();

    List<Statement> statements =
        target.getStatements(twoStatementsText.split(System.lineSeparator()));

    assertNotNull(statements);
    assertEquals(2, statements.size());
  }

  @Test
  void getStatementsWhenTextIsInvalid() {
    List<Statement> statements = target.getStatements(new String[] {"Invalid"});

    assertNotNull(statements);
    assertTrue(statements.isEmpty());
  }
}
