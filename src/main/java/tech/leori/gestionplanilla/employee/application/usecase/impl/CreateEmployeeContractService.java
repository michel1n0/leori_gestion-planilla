package tech.leori.gestionplanilla.employee.application.usecase.impl;

import java.util.Objects;

import tech.leori.gestionplanilla.employee.application.command.CreateEmployeeContractCommand;
import tech.leori.gestionplanilla.employee.application.usecase.CreateEmployeeContractUseCase;
import tech.leori.gestionplanilla.employee.domain.EmployeeContract;
import tech.leori.gestionplanilla.employee.domain.EmployeeContractFactory;
import tech.leori.gestionplanilla.employee.domain.EmployeeContractRepository;

public class CreateEmployeeContractService implements CreateEmployeeContractUseCase {

  private final EmployeeContractRepository employeeContractRepository;

  public CreateEmployeeContractService(EmployeeContractRepository employeeContractRepository) {
    this.employeeContractRepository =
        Objects.requireNonNull(employeeContractRepository, "employeeContractRepository must not be null");
  }

  @Override
  public void create(CreateEmployeeContractCommand command) {
    Objects.requireNonNull(command, "command must not be null");

    EmployeeContract employeeContract =
        EmployeeContractFactory.createContract(
            command.employee(),
            command.startDate(),
            command.salary());

    employeeContractRepository.save(employeeContract);
  }
}
