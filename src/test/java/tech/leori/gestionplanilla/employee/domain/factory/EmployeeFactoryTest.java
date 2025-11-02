package tech.leori.gestionplanilla.employee.domain.factory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import tech.leori.gestionplanilla.employee.domain.Employee;
import tech.leori.gestionplanilla.employee.domain.exception.EmployeeDomainException;

class EmployeeFactoryTest {

  @Test
  @DisplayName("createEmployee should build a valid employee when inputs are correct")
  void createEmployeeShouldReturnValidEmployee() {
    String dni = "12345678";
    String fullName = "John Doe";

    Employee employee = EmployeeFactory.createEmployee(dni, fullName);

    assertThat(employee.getId()).isNotNull();
    assertThat(employee.getFirstName()).isEqualTo("John");
    assertThat(employee.getLastName()).isEqualTo("Doe");
    assertThat(employee.getDocumentType()).isEqualTo("DNI");
    assertThat(employee.getDocumentNumber()).isEqualTo(dni);
    assertThat(employee.getBirthDate()).isEqualTo(LocalDate.now().minusYears(18));
    assertThat(employee.getClientId()).isNotNull();
  }

  @Test
  @DisplayName("createEmployee should throw exception when DNI is invalid")
  void createEmployeeShouldThrowWhenDniInvalid() {
    assertThrows(
        EmployeeDomainException.class,
        () -> EmployeeFactory.createEmployee("12AB567", "John Doe"));
  }

  @Test
  @DisplayName("createEmployee should throw exception when full name is blank")
  void createEmployeeShouldThrowWhenFullNameBlank() {
    assertThrows(
        EmployeeDomainException.class,
        () -> EmployeeFactory.createEmployee("12345678", " "));
  }
}
