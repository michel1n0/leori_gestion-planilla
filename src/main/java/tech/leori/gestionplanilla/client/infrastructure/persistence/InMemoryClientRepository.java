package tech.leori.gestionplanilla.client.infrastructure.persistence;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import tech.leori.gestionplanilla.client.domain.Client;
import tech.leori.gestionplanilla.client.domain.ClientRepository;

public class InMemoryClientRepository implements ClientRepository {

  private final Map<UUID, Client> storage = new ConcurrentHashMap<>();

  @Override
  public Client save(Client client) {
    storage.put(client.getId(), client);
    return client;
  }

  @Override
  public Optional<Client> findById(UUID id) {
    return Optional.ofNullable(storage.get(id));
  }
}
