package tech.leori.consulting.java.arq.clean.gestion.planillas.client.application.usecase;

import tech.leori.consulting.java.arq.clean.gestion.planillas.client.application.command.CreateClientCommand;

public interface CreateClientUseCase {
    void execute(CreateClientCommand command);
}
