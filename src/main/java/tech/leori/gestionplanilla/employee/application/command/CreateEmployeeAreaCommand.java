package tech.leori.gestionplanilla.employee.application.command;

public record CreateEmployeeAreaCommand(Long contractId, String areaName, String areaCode) {}
