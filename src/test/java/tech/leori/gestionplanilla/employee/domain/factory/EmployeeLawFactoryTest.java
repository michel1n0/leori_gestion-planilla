package tech.leori.gestionplanilla.employee.domain.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import tech.leori.gestionplanilla.employee.domain.EmployeeLaw;
import tech.leori.gestionplanilla.employee.domain.exception.InvalidEmployeeLawException;

class EmployeeLawFactoryTest {

  @Test
  @DisplayName("createLaw should return an EmployeeLaw with trimmed values when inputs are valid")
  void createLawWithValidInputs() {
    EmployeeLaw employeeLaw = EmployeeLawFactory.createLaw("  ABC  ", "  General regime  ");

    assertEquals("ABC", employeeLaw.getCode());
    assertEquals("General regime", employeeLaw.getDescription());
  }

  @Test
  @DisplayName("createLaw should throw when the code is null")
  void createLawWithNullCode() {
    assertThrows(InvalidEmployeeLawException.class, () -> EmployeeLawFactory.createLaw(null, "Description"));
  }

  @Test
  @DisplayName("createLaw should throw when the code is blank")
  void createLawWithBlankCode() {
    assertThrows(InvalidEmployeeLawException.class,
        () -> EmployeeLawFactory.createLaw("   ", "Description"));
  }

  @Test
  @DisplayName("createLaw should throw when the code does not match the expected pattern")
  void createLawWithInvalidCodePattern() {
    assertThrows(InvalidEmployeeLawException.class,
        () -> EmployeeLawFactory.createLaw("AB12", "Description"));
  }

  @Test
  @DisplayName("createLaw should throw when the description is null")
  void createLawWithNullDescription() {
    assertThrows(InvalidEmployeeLawException.class, () -> EmployeeLawFactory.createLaw("ABC", null));
  }

  @Test
  @DisplayName("createLaw should throw when the description is blank")
  void createLawWithBlankDescription() {
    assertThrows(InvalidEmployeeLawException.class,
        () -> EmployeeLawFactory.createLaw("ABC", "   "));
  }
}
