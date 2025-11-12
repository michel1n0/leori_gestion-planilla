package tech.leori.gestionplanilla.client.application.usecase;

import tech.leori.gestionplanilla.client.application.command.CreateClientCommand;

public interface CreateClientUseCase {
    void execute(CreateClientCommand command);
}
