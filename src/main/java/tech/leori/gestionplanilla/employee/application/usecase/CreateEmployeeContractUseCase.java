package tech.leori.gestionplanilla.employee.application.usecase;

import tech.leori.gestionplanilla.employee.application.command.CreateEmployeeContractCommand;

public interface CreateEmployeeContractUseCase {
  void create(CreateEmployeeContractCommand command);
}
