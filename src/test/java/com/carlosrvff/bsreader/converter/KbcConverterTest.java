package com.carlosrvff.bsreader.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.carlosrvff.bsreader.domain.Credit;
import com.carlosrvff.bsreader.domain.Debit;
import com.carlosrvff.bsreader.domain.Statement;
import com.carlosrvff.bsreader.exception.InvalidStatementException;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KbcConverterTest {

  private static final String DATE_FIXTURE = "30 Jul 2019";
  private static final String TRANSACTION_FIXTURE = "ANY STORE";
  private static final String FIVE_THOUSAND_AMOUNT_FIXTURE = "5,000.00";

  private KbcConverter target;

  @BeforeEach
  void setUp() {
    target = new KbcConverter();
  }

  @Test
  void toStatementWhenTextIsDebit() throws InvalidStatementException {
    String debitText = createProcessedDebitStatementText();
    Debit expectedDebit = createExpectedDebit(debitText);

    Statement statement = target.toStatement(debitText);

    assertTrue(statement instanceof Debit);
    assertEquals(expectedDebit, statement);
  }

  private String createProcessedDebitStatementText() {
    return new StringBuilder(DATE_FIXTURE)
        .append(target.SEPARATOR)
        .append(target.SEPARATOR)
        .append(TRANSACTION_FIXTURE)
        .append(target.SEPARATOR)
        .append(target.DEBIT_SYMBOL)
        .append(target.CURRENCY_SYMBOL)
        .append(FIVE_THOUSAND_AMOUNT_FIXTURE)
        .append(target.SEPARATOR)
        .append(target.CURRENCY_SYMBOL)
        .append(FIVE_THOUSAND_AMOUNT_FIXTURE)
        .toString();
  }

  private Debit createExpectedDebit(String textFixture) {
    return Debit.builder()
        .originalText(textFixture)
        .value(FIVE_THOUSAND_AMOUNT_FIXTURE)
        .store(TRANSACTION_FIXTURE)
        .date(DATE_FIXTURE)
        .build();
  }

  @Test
  void toStatementWhenTextIsCredit() throws InvalidStatementException {
    String creditText = createProcessedCreditStatementText();
    Credit expectedCredit = createExpectedCredit(creditText);

    Statement statement = target.toStatement(creditText);

    assertTrue(statement instanceof Credit);
    assertEquals(expectedCredit, statement);
  }

  private String createProcessedCreditStatementText() {
    return new StringBuilder(DATE_FIXTURE)
        .append(target.SEPARATOR)
        .append(target.SEPARATOR)
        .append(TRANSACTION_FIXTURE)
        .append(target.SEPARATOR)
        .append(target.CURRENCY_SYMBOL)
        .append(FIVE_THOUSAND_AMOUNT_FIXTURE)
        .append(target.SEPARATOR)
        .append(target.SEPARATOR)
        .append(target.CURRENCY_SYMBOL)
        .append(FIVE_THOUSAND_AMOUNT_FIXTURE)
        .toString();
  }

  private Credit createExpectedCredit(String textFixture) {
    return Credit.builder()
        .originalText(textFixture)
        .from(TRANSACTION_FIXTURE)
        .date(DATE_FIXTURE)
        .value(FIVE_THOUSAND_AMOUNT_FIXTURE)
        .build();
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
    String twoStatementsText = createTwoStatementsText();

    List<Statement> statements = target.toStatements(twoStatementsText.split(System.lineSeparator()));

    assertNotNull(statements);
    assertEquals(2, statements.size());
  }

  private String createTwoStatementsText() {
    String debitText = createOriginalDebitStatementText();
    String creditText = createOriginalCreditStatementText();
    return mergeStrings(debitText, creditText);
  }

  private String createOriginalDebitStatementText() {
    return new StringBuilder(DATE_FIXTURE)
        .append(target.SEPARATOR)
        .append(target.SEPARATOR)
        .append(System.lineSeparator())
        .append(TRANSACTION_FIXTURE)
        .append(target.SEPARATOR)
        .append(System.lineSeparator())
        .append(target.DEBIT_SYMBOL)
        .append(target.CURRENCY_SYMBOL)
        .append(FIVE_THOUSAND_AMOUNT_FIXTURE)
        .append(target.SEPARATOR)
        .append(target.CURRENCY_SYMBOL)
        .append(FIVE_THOUSAND_AMOUNT_FIXTURE)
        .append(System.lineSeparator())
        .toString();
  }

  private String createOriginalCreditStatementText() {
    return new StringBuilder(DATE_FIXTURE)
        .append(target.SEPARATOR)
        .append(target.SEPARATOR)
        .append(System.lineSeparator())
        .append(TRANSACTION_FIXTURE)
        .append(target.SEPARATOR)
        .append(System.lineSeparator())
        .append(target.CURRENCY_SYMBOL)
        .append(FIVE_THOUSAND_AMOUNT_FIXTURE)
        .append(target.SEPARATOR)
        .append(target.SEPARATOR)
        .append(target.CURRENCY_SYMBOL)
        .append(FIVE_THOUSAND_AMOUNT_FIXTURE)
        .append(System.lineSeparator())
        .toString();
  }

  private String mergeStrings(String... texts){
    StringBuilder result = new StringBuilder();
    for (String text : texts){
      result.append(text);
    }
    return result.toString();
  }

  @Test
  void toStatementsWhenTextIsInvalid() throws InvalidStatementException {
    List<Statement> statements = target.toStatements(new String[]{"Invalid"});

    assertNotNull(statements);
    assertTrue(statements.isEmpty());
  }

  @Test
  void getHeader() {
    assertTrue(StringUtils.isNotBlank(target.getHeader()));
  }
}
