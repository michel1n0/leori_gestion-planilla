package tech.leori.gestionplanilla.client.application.usecase.impl;

import java.util.Objects;

import tech.leori.gestionplanilla.client.application.command.CreateClientCommand;
import tech.leori.gestionplanilla.client.application.usecase.CreateClientUseCase;
import tech.leori.gestionplanilla.client.application.ports.out.ClientRepository;
import tech.leori.gestionplanilla.client.domain.Client;
import tech.leori.gestionplanilla.client.domain.factory.ClientFactory;

public class CreateClientService implements CreateClientUseCase {

    private final ClientRepository clientRepository;
    private final ClientFactory clientFactory;

    public CreateClientService(ClientRepository clientRepository, ClientFactory clientFactory) {
        this.clientRepository = Objects.requireNonNull(clientRepository, "clientRepository must not be null");
        this.clientFactory = Objects.requireNonNull(clientFactory, "clientFactory must not be null");
    }

    @Override
    public void execute(CreateClientCommand command) {
        Objects.requireNonNull(command, "command must not be null");

        Client client = clientFactory.create(command.name(), command.ruc(), command.industryType());
        clientRepository.save(client);
    }
}
