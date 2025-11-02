package tech.leori.gestionplanilla.client.infrastructure.persistence;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import tech.leori.gestionplanilla.client.application.ports.out.ClientRepository;
import tech.leori.gestionplanilla.client.domain.Client;

public class InMemoryClientRepository implements ClientRepository {

  private final Map<UUID, Client> storage = new ConcurrentHashMap<>();

  @Override
  public Optional<Client> findByRuc(String ruc) {
    return storage.values().stream()
        .filter(client -> client.getRuc().equals(ruc))
        .findFirst();
  }

  @Override
  public Client save(Client client) {
    storage.put(client.getId(), client);
    return client;
  }
}
