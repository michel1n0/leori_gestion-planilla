package tech.leori.gestionplanilla.employee.application.usecase;

import tech.leori.gestionplanilla.employee.application.command.CreateEmployeeLawCommand;

public interface CreateEmployeeLawUseCase {

  void create(CreateEmployeeLawCommand command);
}
