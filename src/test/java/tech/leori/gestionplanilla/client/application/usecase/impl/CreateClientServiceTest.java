package tech.leori.gestionplanilla.client.application.usecase.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import tech.leori.gestionplanilla.client.application.command.CreateClientCommand;
import tech.leori.gestionplanilla.client.application.ports.out.ClientRepository;
import tech.leori.gestionplanilla.client.domain.Client;
import tech.leori.gestionplanilla.client.domain.factory.ClientFactory;

@ExtendWith(MockitoExtension.class)
class CreateClientServiceTest {

    private InMemoryClientRepository clientRepository;

    @Mock
    private ClientFactory clientFactory;

    private CreateClientService createClientService;

    @BeforeEach
    void setUp() {
        clientRepository = new InMemoryClientRepository();
        createClientService = new CreateClientService(clientRepository, clientFactory);
    }

    @Test
    void execute_shouldCreateClientAndPersistItWithGivenValues() {
        CreateClientCommand command = new CreateClientCommand(1L, "ACME Corp", "12345678901", "Technology");
        Client expectedClient = Client.builder()
                .withBussinessName(command.name())
                .withRuc(command.ruc())
                .withIndustryType(command.industryType())
                .build();

        when(clientFactory.create(command.name(), command.ruc(), command.industryType())).thenReturn(expectedClient);

        createClientService.execute(command);

        verify(clientFactory).create(command.name(), command.ruc(), command.industryType());
        Client savedClient = clientRepository.getSavedClient().orElseThrow();
        assertSame(expectedClient, savedClient);
        assertEquals(command.name(), savedClient.getBusinessName());
        assertEquals(command.ruc(), savedClient.getRuc());
        assertEquals(command.industryType(), savedClient.getIndustryType());
    }

    private static class InMemoryClientRepository implements ClientRepository {

        private Client savedClient;

        @Override
        public Optional<Client> findByRuc(String ruc) {
            if (savedClient == null || !savedClient.getRuc().equals(ruc)) {
                return Optional.empty();
            }
            return Optional.of(savedClient);
        }

        @Override
        public Client save(Client client) {
            this.savedClient = client;
            return client;
        }

        Optional<Client> getSavedClient() {
            return Optional.ofNullable(savedClient);
        }
    }
}
