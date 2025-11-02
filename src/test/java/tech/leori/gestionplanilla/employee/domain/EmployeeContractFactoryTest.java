package tech.leori.gestionplanilla.employee.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import tech.leori.gestionplanilla.employee.domain.exception.EmployeeDomainException;

class EmployeeContractFactoryTest {

  @Test
  void createContractShouldReturnContractWithDefaultValues() {
    Employee employee = EmployeeFactory.createEmployee("12345678", "John Doe");
    LocalDate startDate = LocalDate.now().minusDays(1);
    BigDecimal salary = new BigDecimal("1500.00");

    EmployeeContract contract = EmployeeContractFactory.createContract(employee, startDate, salary);

    assertNotNull(contract);
    assertEquals(employee.getId(), contract.getEmployeeId());
    assertEquals(startDate, contract.getStartDate());
    assertEquals(salary, contract.getSalary());
    assertEquals("INDEFINITE", contract.getContractType());
    assertNotNull(contract.getContractId());
  }

  @Test
  void createContractShouldFailWhenEmployeeIsNull() {
    LocalDate startDate = LocalDate.now().minusDays(1);
    BigDecimal salary = new BigDecimal("1500.00");

    assertThrows(
        EmployeeDomainException.class,
        () -> EmployeeContractFactory.createContract(null, startDate, salary));
  }

  @Test
  void createContractShouldFailWhenStartDateIsNull() {
    Employee employee = EmployeeFactory.createEmployee("12345678", "John Doe");
    BigDecimal salary = new BigDecimal("1500.00");

    assertThrows(
        EmployeeDomainException.class,
        () -> EmployeeContractFactory.createContract(employee, null, salary));
  }

  @Test
  void createContractShouldFailWhenStartDateIsInFuture() {
    Employee employee = EmployeeFactory.createEmployee("12345678", "John Doe");
    LocalDate futureStartDate = LocalDate.now().plusDays(1);
    BigDecimal salary = new BigDecimal("1500.00");

    assertThrows(
        EmployeeDomainException.class,
        () -> EmployeeContractFactory.createContract(employee, futureStartDate, salary));
  }

  @ParameterizedTest
  @ValueSource(strings = {"0", "-100"})
  void createContractShouldFailWhenSalaryIsNotPositive(String salaryValue) {
    Employee employee = EmployeeFactory.createEmployee("12345678", "John Doe");
    LocalDate startDate = LocalDate.now().minusDays(1);
    BigDecimal salary = new BigDecimal(salaryValue);

    assertThrows(
        EmployeeDomainException.class,
        () -> EmployeeContractFactory.createContract(employee, startDate, salary));
  }
}
