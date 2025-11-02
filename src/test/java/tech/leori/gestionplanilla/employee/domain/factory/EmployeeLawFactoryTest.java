package tech.leori.gestionplanilla.employee.domain.factory;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    EmployeeLaw employeeLaw = EmployeeLawFactory.createLaw("  ABC  ", "  General regime  ");

    assertAll(
        () -> assertEquals("ABC", employeeLaw.getCode()),
        () -> assertEquals("General regime", employeeLaw.getDescription()));
  }

  @ParameterizedTest
  @NullSource
  @ValueSource(strings = {"", "   ", "AB12", "abc", "A", "TOOLONG"})
  @DisplayName("createLaw should throw when the code is null, blank, or has an invalid format")
  void createLawWithInvalidCodes(String code) {
    assertThrows(
        InvalidEmployeeLawException.class,
        () -> EmployeeLawFactory.createLaw(code, "Description"));
  }

  @ParameterizedTest
  @NullSource
  @ValueSource(strings = {"", "   "})
  @DisplayName("createLaw should throw when the description is null or blank")
  void createLawWithInvalidDescriptions(String description) {
    assertThrows(
        InvalidEmployeeLawException.class,
        () -> EmployeeLawFactory.createLaw("ABC", description));
  }
}
