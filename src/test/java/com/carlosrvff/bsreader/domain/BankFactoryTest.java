package com.carlosrvff.bsreader.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.carlosrvff.bsreader.domain.bank.Cheetah;
import com.carlosrvff.bsreader.domain.bank.ItauCsv;
import com.carlosrvff.bsreader.domain.bank.ItauSite;
import com.carlosrvff.bsreader.domain.bank.Kbc;
import com.carlosrvff.bsreader.domain.bank.Revolut;
import org.junit.jupiter.api.Test;

class BankFactoryTest {

  @Test
  void getBankWhenInputIsCheetah() {
    Bank bank = BankFactory.getBank(Cheetah.getHeader());
    assertTrue(bank instanceof Cheetah);
  }

  @Test
  void getBankWhenInputIsItauCsv() {
    Bank bank = BankFactory.getBank(ItauCsv.getHeader());
    assertTrue(bank instanceof ItauCsv);
  }

  @Test
  void getBankWhenInputIsItauSite() {
    Bank bank = BankFactory.getBank(ItauSite.getHeader());
    assertTrue(bank instanceof ItauSite);
  }

  @Test
  void getBankWhenInputIsKbc() {
    Bank bank = BankFactory.getBank(Kbc.getHeader());
    assertTrue(bank instanceof Kbc);
  }

  @Test
  void getBankWhenInputIsRevolut() {
    Bank bank = BankFactory.getBank(Revolut.getHeader());
    assertTrue(bank instanceof Revolut);
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
