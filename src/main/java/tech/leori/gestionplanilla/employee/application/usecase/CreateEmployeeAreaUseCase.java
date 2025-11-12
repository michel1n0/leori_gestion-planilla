package tech.leori.gestionplanilla.employee.application.usecase;

import tech.leori.gestionplanilla.employee.application.command.CreateEmployeeAreaCommand;

public interface CreateEmployeeAreaUseCase {
  void create(CreateEmployeeAreaCommand command);
}
