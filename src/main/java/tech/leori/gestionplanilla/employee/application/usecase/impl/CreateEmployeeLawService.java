package tech.leori.gestionplanilla.employee.application.usecase.impl;

import java.util.Objects;
import java.util.UUID;

import tech.leori.gestionplanilla.employee.application.command.CreateEmployeeLawCommand;
import tech.leori.gestionplanilla.employee.application.usecase.CreateEmployeeLawUseCase;
import tech.leori.gestionplanilla.employee.domain.EmployeeContractRepository;
import tech.leori.gestionplanilla.employee.domain.EmployeeLaw;
import tech.leori.gestionplanilla.employee.domain.exception.EmployeeContractNotFoundException;
import tech.leori.gestionplanilla.employee.domain.factory.EmployeeLawFactory;

public class CreateEmployeeLawService implements CreateEmployeeLawUseCase {

  private final EmployeeContractRepository employeeContractRepository;

  public CreateEmployeeLawService(EmployeeContractRepository employeeContractRepository) {
    this.employeeContractRepository =
        Objects.requireNonNull(
            employeeContractRepository, "employeeContractRepository must not be null");
  }

  @Override
  public void create(CreateEmployeeLawCommand command) {
    Objects.requireNonNull(command, "command must not be null");

    UUID contractId = Objects.requireNonNull(command.contractId(), "contractId must not be null");

    employeeContractRepository
        .findById(contractId)
        .orElseThrow(
            () ->
                new EmployeeContractNotFoundException(
                    "Employee contract with id " + contractId + " was not found"));

    EmployeeLaw employeeLaw =
        EmployeeLawFactory.createLaw(
            command.lawCode(), command.lawDescription(), command.lawContribution());

    employeeContractRepository.addLaw(contractId, employeeLaw);
  }
}
