package tech.leori.gestionplanilla.employee.application.usecase.impl;

import java.util.Objects;

import tech.leori.gestionplanilla.employee.application.command.CreateEmployeeCommand;
import tech.leori.gestionplanilla.employee.application.usecase.CreateEmployeeUseCase;
import tech.leori.gestionplanilla.employee.domain.Employee;
import tech.leori.gestionplanilla.employee.domain.EmployeeFactory;
import tech.leori.gestionplanilla.employee.domain.EmployeeRepository;

public class CreateEmployeeService implements CreateEmployeeUseCase {

  private final EmployeeRepository employeeRepository;

  public CreateEmployeeService(EmployeeRepository employeeRepository) {
    this.employeeRepository =
        Objects.requireNonNull(employeeRepository, "employeeRepository must not be null");
  }

  @Override
  public void create(CreateEmployeeCommand command) {
    Objects.requireNonNull(command, "command must not be null");

    Employee employee = EmployeeFactory.createEmployee(command.dni(), command.fullName());
    employeeRepository.save(employee);
  }
}
