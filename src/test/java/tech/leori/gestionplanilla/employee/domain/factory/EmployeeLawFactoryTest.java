package tech.leori.gestionplanilla.employee.domain.factory;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import tech.leori.gestionplanilla.employee.domain.EmployeeLaw;
import tech.leori.gestionplanilla.employee.domain.exception.InvalidEmployeeLawException;

class EmployeeLawFactoryTest {

  @Test
  @DisplayName("createLaw should return a trimmed EmployeeLaw when inputs are valid")
  void createLawWithValidInputs() {
    EmployeeLaw employeeLaw =
        EmployeeLawFactory.createLaw("  ABC  ", "  General regime  ", new BigDecimal("9.75"));

    assertAll(
        () -> assertEquals("ABC", employeeLaw.getCode()),
        () -> assertEquals("General regime", employeeLaw.getDescription()),
        () -> assertEquals(new BigDecimal("9.75"), employeeLaw.getContributionPercentage()));
  }

  @ParameterizedTest
  @NullSource
  @ValueSource(strings = {"", "   ", "AB12", "abc", "A", "TOOLONG"})
  @DisplayName("createLaw should throw when the code is null, blank, or has an invalid format")
  void createLawWithInvalidCodes(String code) {
    assertThrows(
        InvalidEmployeeLawException.class,
        () -> EmployeeLawFactory.createLaw(code, "Description", BigDecimal.ZERO));
  }

  @ParameterizedTest
  @NullSource
  @ValueSource(strings = {"", "   "})
  @DisplayName("createLaw should throw when the description is null or blank")
  void createLawWithInvalidDescriptions(String description) {
    assertThrows(
        InvalidEmployeeLawException.class,
        () -> EmployeeLawFactory.createLaw("ABC", description, BigDecimal.ZERO));
  }

  @ParameterizedTest
  @NullSource
  @ValueSource(strings = {"-0.01"})
  @DisplayName("createLaw should throw when the contribution is null or negative")
  void createLawWithInvalidContribution(String rawContribution) {
    BigDecimal contribution = rawContribution == null ? null : new BigDecimal(rawContribution);

    assertThrows(
        InvalidEmployeeLawException.class,
        () -> EmployeeLawFactory.createLaw("ABC", "Description", contribution));
  }
}
