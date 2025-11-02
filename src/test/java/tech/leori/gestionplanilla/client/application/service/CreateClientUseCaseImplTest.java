package tech.leori.gestionplanilla.client.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import tech.leori.gestionplanilla.client.application.ports.in.commands.CreateClientCommand;
import tech.leori.gestionplanilla.client.application.ports.out.ClientRepository;
import tech.leori.gestionplanilla.client.domain.Client;
import tech.leori.gestionplanilla.client.domain.exception.ClientException;
import tech.leori.gestionplanilla.client.domain.factory.ClientFactory;
import tech.leori.gestionplanilla.client.domain.factory.ClientFactoryImpl;

@ExtendWith(MockitoExtension.class)
class CreateClientUseCaseImplTest {

    @Mock
    private ClientRepository clientRepository;

    private ClientFactory clientFactory;

    private CreateClientUseCaseImpl useCase;

    @BeforeEach
    void setUp() {
        clientFactory = new ClientFactoryImpl();
        useCase = new CreateClientUseCaseImpl(clientRepository, clientFactory);
    }

    @Test
    void handleShouldSaveClientAndReturnIdWhenRucIsUnique() {
        CreateClientCommand command = new CreateClientCommand("Acme Corp", "12345678901", "Technology");
        UUID generatedId = UUID.randomUUID();
        Client savedClient = Client.builder()
                .withId(generatedId)
                .withBussinessName(command.businessName())
                .withRuc(command.ruc())
                .withIndustryType(command.industryType())
                .build();

        when(clientRepository.findByRuc(command.ruc())).thenReturn(Optional.empty());
        when(clientRepository.save(any(Client.class))).thenReturn(savedClient);

        UUID result = useCase.handle(command);

        assertEquals(generatedId, result);

        verify(clientRepository).findByRuc(command.ruc());
        ArgumentCaptor<Client> clientCaptor = ArgumentCaptor.forClass(Client.class);
        verify(clientRepository).save(clientCaptor.capture());
        Client clientToSave = clientCaptor.getValue();
        assertEquals(command.businessName(), clientToSave.getBusinessName());
        assertEquals(command.ruc(), clientToSave.getRuc());
        assertEquals(command.industryType(), clientToSave.getIndustryType());
    }

    @Test
    void handleShouldThrowExceptionWhenRucAlreadyExists() {
        CreateClientCommand command = new CreateClientCommand("Acme Corp", "12345678901", "Technology");
        Client existingClient = Client.builder()
                .withId(UUID.randomUUID())
                .withBussinessName("Existing Corp")
                .withRuc(command.ruc())
                .withIndustryType("Finance")
                .build();

        when(clientRepository.findByRuc(command.ruc())).thenReturn(Optional.of(existingClient));

        assertThrows(ClientException.class, () -> useCase.handle(command));

        verify(clientRepository).findByRuc(command.ruc());
        verify(clientRepository, never()).save(any(Client.class));
    }
}
