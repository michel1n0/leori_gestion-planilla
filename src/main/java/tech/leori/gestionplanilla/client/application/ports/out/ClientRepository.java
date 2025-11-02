package tech.leori.gestionplanilla.client.application.ports.out;

import java.util.Optional;

import tech.leori.gestionplanilla.client.domain.Client;

public interface ClientRepository {

    Optional<Client> findByRuc(String ruc);

    Client save(Client client);
}
