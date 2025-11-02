package tech.leori.gestionplanilla.client.domain.factory;

import tech.leori.gestionplanilla.client.domain.Client;

public final class ClientFactory {

    private ClientFactory() {
    }

    public static Client create(String businessName, String ruc, String industryType) {
        return Client.builder()
                .withBussinessName(businessName)
                .withRuc(ruc)
                .withIndustryType(industryType)
                .build();
    }
}
