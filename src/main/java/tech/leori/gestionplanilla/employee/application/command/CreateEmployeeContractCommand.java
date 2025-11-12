package tech.leori.gestionplanilla.employee.application.command;

import java.math.BigDecimal;
import java.time.LocalDate;

import tech.leori.gestionplanilla.employee.domain.Employee;

public record CreateEmployeeContractCommand(
    Employee employee,
    LocalDate startDate,
    BigDecimal salary) {}
