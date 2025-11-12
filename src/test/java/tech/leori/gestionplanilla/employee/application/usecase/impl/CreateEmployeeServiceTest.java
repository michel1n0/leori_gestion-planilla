package tech.leori.gestionplanilla.employee.application.usecase.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import tech.leori.gestionplanilla.employee.application.command.CreateEmployeeCommand;
import tech.leori.gestionplanilla.employee.domain.Employee;
import tech.leori.gestionplanilla.employee.domain.EmployeeRepository;
import tech.leori.gestionplanilla.employee.domain.PensionType;

class CreateEmployeeServiceTest {

  private EmployeeRepository employeeRepository;
  private CreateEmployeeService service;

  @BeforeEach
  void setUp() {
    employeeRepository = Mockito.mock(EmployeeRepository.class);
    service = new CreateEmployeeService(employeeRepository);
  }

  @Test
  @DisplayName("create should build and persist an employee using the command data")
  void createShouldBuildAndPersistEmployee() {
    CreateEmployeeCommand command =
        new CreateEmployeeCommand(
            1L,
            "John",
            "Doe",
            "12345678",
            new BigDecimal("2500.00"),
            PensionType.AFP,
            LocalDate.now().minusMonths(1),
            true);

    when(employeeRepository.save(Mockito.any(Employee.class)))
        .thenAnswer(invocation -> invocation.getArgument(0));

    service.create(command);

    ArgumentCaptor<Employee> employeeCaptor = ArgumentCaptor.forClass(Employee.class);
    verify(employeeRepository).save(employeeCaptor.capture());
    verifyNoMoreInteractions(employeeRepository);

    Employee savedEmployee = employeeCaptor.getValue();
    assertNotNull(savedEmployee, "Employee saved to repository should not be null");
    assertNotNull(savedEmployee.getId(), "Employee id should be generated");
    assertEquals("John", savedEmployee.getFirstName());
    assertEquals("Doe", savedEmployee.getLastName());
    assertEquals("DNI", savedEmployee.getDocumentType());
    assertEquals("12345678", savedEmployee.getDocumentNumber());
  }
}
