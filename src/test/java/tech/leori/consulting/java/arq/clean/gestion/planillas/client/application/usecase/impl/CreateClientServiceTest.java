package tech.leori.consulting.java.arq.clean.gestion.planillas.client.application.usecase.impl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import tech.leori.consulting.java.arq.clean.gestion.planillas.client.application.command.CreateClientCommand;
import tech.leori.gestionplanilla.client.application.ports.out.ClientRepository;
import tech.leori.gestionplanilla.client.domain.Client;
import tech.leori.gestionplanilla.client.domain.factory.ClientFactory;

@ExtendWith(MockitoExtension.class)
class CreateClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientFactory clientFactory;

    @InjectMocks
    private CreateClientService createClientService;

    @Test
    void execute_shouldCreateClientAndSaveIt() {
        CreateClientCommand command = new CreateClientCommand(1L, "ACME Corp", "12345678901", "Technology");
        Client client = Client.builder()
                .withBussinessName(command.name())
                .withRuc(command.ruc())
                .withIndustryType(command.industryType())
                .build();

        when(clientFactory.create(command.name(), command.ruc(), command.industryType())).thenReturn(client);

        createClientService.execute(command);

        verify(clientFactory).create(command.name(), command.ruc(), command.industryType());
        verify(clientRepository).save(client);
    }
}
