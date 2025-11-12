package tech.leori.gestionplanilla.employee.application.command;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateEmployeeLawCommand(
    UUID contractId,
    String lawCode,
    String lawDescription,
    BigDecimal lawContribution) {}
