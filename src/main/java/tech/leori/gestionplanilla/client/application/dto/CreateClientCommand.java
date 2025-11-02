package tech.leori.gestionplanilla.client.application.dto;

import java.util.Objects;

public record CreateClientCommand(String name, String ruc, String address) {

    public CreateClientCommand {
        name = Objects.requireNonNull(name, "name must not be null").trim();
        ruc = Objects.requireNonNull(ruc, "ruc must not be null").trim();
        address = Objects.requireNonNull(address, "address must not be null").trim();

        if (name.isEmpty()) {
            throw new IllegalArgumentException("name must not be blank");
        }
        if (ruc.isEmpty()) {
            throw new IllegalArgumentException("ruc must not be blank");
        }
        if (address.isEmpty()) {
            throw new IllegalArgumentException("address must not be blank");
        }
    }
}
