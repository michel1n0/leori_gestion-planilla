package tech.leori.gestionplanilla.client.domain;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository {

  Client save(Client client);

  Optional<Client> findById(UUID id);
}
