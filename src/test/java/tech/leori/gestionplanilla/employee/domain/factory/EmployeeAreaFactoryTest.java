package tech.leori.gestionplanilla.employee.domain.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import tech.leori.gestionplanilla.employee.domain.EmployeeArea;
import tech.leori.gestionplanilla.employee.domain.exception.InvalidEmployeeAreaNameException;

class EmployeeAreaFactoryTest {

  @Test
  @DisplayName("createArea should return a populated EmployeeArea when the name is valid")
  void createAreaWithValidName() {
    String nameWithWhitespace = "  Finance  ";

    EmployeeArea employeeArea = EmployeeAreaFactory.createArea(nameWithWhitespace);

    assertNotNull(employeeArea.getContractId(), "Contract id should be generated");
    assertNotNull(employeeArea.getAreaId(), "Area id should be generated");
    assertEquals("Finance", employeeArea.getAreaName(), "Area name should be trimmed");
    assertEquals(LocalDate.now(), employeeArea.getAssignmentDate(),
        "Assignment date should be set to the current date");
  }

  @Test
  @DisplayName("createArea should throw when the name is null")
  void createAreaWithNullName() {
    assertThrows(InvalidEmployeeAreaNameException.class, () -> EmployeeAreaFactory.createArea(null));
  }

  @Test
  @DisplayName("createArea should throw when the name is blank")
  void createAreaWithBlankName() {
    assertThrows(InvalidEmployeeAreaNameException.class, () -> EmployeeAreaFactory.createArea("   "));
  }

  @Test
  @DisplayName("createArea should throw when the name has less than 3 characters")
  void createAreaWithShortName() {
    assertThrows(InvalidEmployeeAreaNameException.class, () -> EmployeeAreaFactory.createArea("AB"));
  }
}
