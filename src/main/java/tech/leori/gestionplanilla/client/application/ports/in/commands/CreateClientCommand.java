package tech.leori.gestionplanilla.client.application.ports.in.commands;

import java.util.Objects;

public record CreateClientCommand(String businessName, String ruc, String industryType) {

    public CreateClientCommand {
        businessName = Objects.requireNonNull(businessName, "businessName must not be null").trim();
        ruc = Objects.requireNonNull(ruc, "ruc must not be null").trim();
        industryType = Objects.requireNonNull(industryType, "industryType must not be null").trim();

        if (businessName.isEmpty()) {
            throw new IllegalArgumentException("businessName must not be blank");
        }
        if (ruc.isEmpty()) {
            throw new IllegalArgumentException("ruc must not be blank");
        }
        if (industryType.isEmpty()) {
            throw new IllegalArgumentException("industryType must not be blank");
        }
    }
}
