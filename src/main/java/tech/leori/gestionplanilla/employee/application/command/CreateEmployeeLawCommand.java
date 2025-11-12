package tech.leori.gestionplanilla.employee.application.command;

import java.util.UUID;

public record CreateEmployeeLawCommand(
    UUID contractId,
    String lawCode,
    String lawDescription) {}
