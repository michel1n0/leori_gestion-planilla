package tech.leori.gestionplanilla.employee.application.usecase;

import tech.leori.gestionplanilla.employee.application.command.CreateEmployeeCommand;

public interface CreateEmployeeUseCase {
  void create(CreateEmployeeCommand command);
}
