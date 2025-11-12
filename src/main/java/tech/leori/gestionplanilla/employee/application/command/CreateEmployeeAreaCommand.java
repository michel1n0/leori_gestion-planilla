package tech.leori.gestionplanilla.employee.application.command;

import java.util.UUID;

public record CreateEmployeeAreaCommand(UUID contractId, String areaName) {}
