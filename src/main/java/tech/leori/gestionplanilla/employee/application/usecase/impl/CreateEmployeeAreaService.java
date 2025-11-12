package tech.leori.gestionplanilla.employee.application.usecase.impl;

import java.util.Objects;
import java.util.UUID;

import tech.leori.gestionplanilla.employee.application.command.CreateEmployeeAreaCommand;
import tech.leori.gestionplanilla.employee.application.usecase.CreateEmployeeAreaUseCase;
import tech.leori.gestionplanilla.employee.domain.EmployeeArea;
import tech.leori.gestionplanilla.employee.domain.EmployeeContractRepository;
import tech.leori.gestionplanilla.employee.domain.exception.EmployeeContractNotFoundException;
import tech.leori.gestionplanilla.employee.domain.factory.EmployeeAreaFactory;

public class CreateEmployeeAreaService implements CreateEmployeeAreaUseCase {

  private final EmployeeContractRepository employeeContractRepository;

  public CreateEmployeeAreaService(EmployeeContractRepository employeeContractRepository) {
    this.employeeContractRepository =
        Objects.requireNonNull(
            employeeContractRepository, "employeeContractRepository must not be null");
  }

  @Override
  public void create(CreateEmployeeAreaCommand command) {
    Objects.requireNonNull(command, "command must not be null");

    UUID contractId = Objects.requireNonNull(command.contractId(), "contractId must not be null");

    employeeContractRepository
        .findById(contractId)
        .orElseThrow(
            () ->
                new EmployeeContractNotFoundException(
                    "Employee contract with id " + contractId + " was not found"));

    EmployeeArea employeeArea =
        EmployeeAreaFactory.createArea(command.areaName()).toBuilder().withContractId(contractId).build();

    employeeContractRepository.addArea(employeeArea);
  }
}
