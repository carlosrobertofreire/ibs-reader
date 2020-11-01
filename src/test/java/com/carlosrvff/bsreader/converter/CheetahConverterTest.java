package com.carlosrvff.bsreader.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.carlosrvff.bsreader.domain.Credit;
import com.carlosrvff.bsreader.domain.Debit;
import com.carlosrvff.bsreader.domain.Statement;
import com.carlosrvff.bsreader.exception.InvalidStatementException;
import com.carlosrvff.bsreader.helper.CheetahStatementUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CheetahConverterTest {

  private CheetahConverter target;

  @BeforeEach
  void setUp() {
    target = new CheetahConverter();
  }

  @Test
  void toStatementWhenTextIsFeeDebit() throws InvalidStatementException {
    String textFixture = CheetahStatementUtils.createFeeDebitStatementText();
    Debit expectedFeeDebit = CheetahStatementUtils.createFeeDebit(textFixture);

    Statement statement = target.toStatement(textFixture);

    assertTrue(statement instanceof Debit);
    assertEquals(expectedFeeDebit, statement);
  }

  @Test
  void toStatementWhenTextIsPurchaseDebit() throws InvalidStatementException {
    String textFixture = CheetahStatementUtils.createPurchaseDebitStatementText();
    Debit expectedPurchaseDebit = CheetahStatementUtils.createPurchaseDebit(textFixture);

    Statement statement = target.toStatement(textFixture);

    assertTrue(statement instanceof Debit);
    assertEquals(expectedPurchaseDebit, statement);
  }

  @Test
  void toStatementWhenTextIsCredit() throws InvalidStatementException {
    String textFixture = CheetahStatementUtils.createCreditStatementText();
    Credit expectedCredit = CheetahStatementUtils.createCredit(textFixture);

    Statement statement = target.toStatement(textFixture);

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
  void getHeader() {
    assertTrue(StringUtils.isNotBlank(target.getHeader()));
  }
}
