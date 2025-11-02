package tech.leori.gestionplanilla.client.domain.factory;

import tech.leori.gestionplanilla.client.domain.Client;

public interface ClientFactory {

    Client create(String name, String ruc, String address);
}
