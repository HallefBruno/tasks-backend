
package br.ce.wcaquino.taskbackend.utils;

import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DateUtilsTest {
  
  
  void setUp() {
  }
  
  @Test
  void deveRetornarFalseParaDataFutura() {
    LocalDate agora = LocalDate.of(2030, Month.MARCH, 20);
    Assertions.assertTrue(DateUtils.isEqualOrFutureDate(agora));
  }
  
  @Test
  void deveRetornarFalseParaDataPassada() {
    LocalDate agora = LocalDate.of(2010, Month.MARCH, 20);
    Assertions.assertFalse(DateUtils.isEqualOrFutureDate(agora));
  }
  
  @Test
  void deveRetornarTrueParaDataAtual() {
    LocalDate agora = LocalDate.now();
    Assertions.assertTrue(DateUtils.isEqualOrFutureDate(agora));
  }
  
}
