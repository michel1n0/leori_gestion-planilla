package tech.leori.gestionplanilla.client.application.service;

import java.util.Objects;
import java.util.UUID;

import tech.leori.gestionplanilla.client.application.ports.in.CreateClientUseCase;
import tech.leori.gestionplanilla.client.application.ports.in.commands.CreateClientCommand;
import tech.leori.gestionplanilla.client.application.ports.out.ClientRepository;
import tech.leori.gestionplanilla.client.domain.Client;
import tech.leori.gestionplanilla.client.domain.exception.ClientException;
import tech.leori.gestionplanilla.client.domain.factory.ClientFactory;
import tech.leori.gestionplanilla.client.domain.factory.ClientFactoryImpl;

public class CreateClientUseCaseImpl implements CreateClientUseCase {

    private final ClientRepository clientRepository;
    private final ClientFactory clientFactory;

    public CreateClientUseCaseImpl(ClientRepository clientRepository) {
        this(clientRepository, new ClientFactoryImpl());
    }

    public CreateClientUseCaseImpl(ClientRepository clientRepository, ClientFactory clientFactory) {
        this.clientRepository = Objects.requireNonNull(clientRepository, "clientRepository must not be null");
        this.clientFactory = Objects.requireNonNull(clientFactory, "clientFactory must not be null");
    }

    @Override
    public UUID handle(CreateClientCommand command) {
        Objects.requireNonNull(command, "command must not be null");

        clientRepository.findByRuc(command.ruc()).ifPresent(existing -> {
            throw new ClientException("Client with RUC %s already exists".formatted(command.ruc()));
        });

        Client client = clientFactory.create(command.businessName(), command.ruc(), command.industryType());
        Client savedClient = clientRepository.save(client);

        return savedClient.getId();
    }
}
