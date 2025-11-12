package tech.leori.gestionplanilla.employee.application.command;

import java.math.BigDecimal;
import java.time.LocalDate;

import tech.leori.gestionplanilla.employee.domain.PensionType;

public record CreateEmployeeCommand(
    Long clientId,
    String name,
    String lastName,
    String dni,
    BigDecimal baseSalary,
    PensionType pensionType,
    LocalDate startDate,
    boolean insurance) {}
