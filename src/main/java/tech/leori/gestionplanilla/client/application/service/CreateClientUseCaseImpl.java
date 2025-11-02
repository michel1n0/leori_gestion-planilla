package tech.leori.gestionplanilla.client.application.service;

import java.util.Objects;
import java.util.UUID;

import tech.leori.gestionplanilla.client.application.ports.in.CreateClientUseCase;
import tech.leori.gestionplanilla.client.application.ports.in.commands.CreateClientCommand;
import tech.leori.gestionplanilla.client.application.ports.out.ClientRepository;
import tech.leori.gestionplanilla.client.domain.Client;
import tech.leori.gestionplanilla.client.domain.exception.ClientException;
import tech.leori.gestionplanilla.client.domain.factory.ClientFactory;

public class CreateClientUseCaseImpl implements CreateClientUseCase {

    private final ClientRepository clientRepository;

    public CreateClientUseCaseImpl(ClientRepository clientRepository) {
        this.clientRepository = Objects.requireNonNull(clientRepository, "clientRepository must not be null");
    }

    @Override
    public UUID handle(CreateClientCommand command) {
        Objects.requireNonNull(command, "command must not be null");

        clientRepository.findByRuc(command.ruc()).ifPresent(existing -> {
            throw new ClientException("Client with RUC %s already exists".formatted(command.ruc()));
        });

        Client client = ClientFactory.create(command.businessName(), command.ruc(), command.industryType());
        Client savedClient = clientRepository.save(client);

        return savedClient.getId();
    }
}
