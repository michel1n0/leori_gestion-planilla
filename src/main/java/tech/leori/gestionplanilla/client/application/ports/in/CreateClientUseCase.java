package tech.leori.gestionplanilla.client.application.ports.in;

import tech.leori.gestionplanilla.client.application.ports.in.commands.CreateClientCommand;

public interface CreateClientUseCase {
    Long handle(CreateClientCommand command);
}
