package com.carlosrvff.bsreader.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.carlosrvff.bsreader.domain.bank.Cheetah;
import org.junit.jupiter.api.Test;

class BankFactoryTest {

  @Test
  void getBankWhenInputIsCheetah() {
    Bank bank = BankFactory.getBank(Cheetah.getHeader());
    assertTrue(bank instanceof Cheetah);
  }

  @Test
  void getBankWhenInputIsNull() {
    assertThrows(IllegalArgumentException.class, () -> BankFactory.getBank(null));
  }

  @Test
  void getBankWhenInputIsBlank() {
    assertThrows(IllegalArgumentException.class, () -> BankFactory.getBank("  "));
  }

  @Test
  void getBankWhenInputDoesHaveOneLine() {
    assertThrows(IllegalArgumentException.class, () -> BankFactory.getBank("Just one line"));
  }
}
