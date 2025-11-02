package tech.leori.gestionplanilla.client.application.ports.in;

import java.util.UUID;

import tech.leori.gestionplanilla.client.application.ports.in.commands.CreateClientCommand;

public interface CreateClientUseCase {
    UUID handle(CreateClientCommand command);
}
