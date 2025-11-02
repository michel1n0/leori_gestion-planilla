package tech.leori.gestionplanilla.client.domain.factory;

import tech.leori.gestionplanilla.client.domain.Client;

public class ClientFactoryImpl implements ClientFactory {

    @Override
    public Client create(String name, String ruc, String address) {
        return Client.builder()
                .withBussinessName(name)
                .withRuc(ruc)
                .withIndustryType(address)
                .build();
    }
}
