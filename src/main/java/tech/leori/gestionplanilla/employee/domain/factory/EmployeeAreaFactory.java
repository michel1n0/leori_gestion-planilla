package tech.leori.gestionplanilla.employee.domain.factory;

import java.time.LocalDate;
import java.util.UUID;

import tech.leori.gestionplanilla.employee.domain.EmployeeArea;
import tech.leori.gestionplanilla.employee.domain.exception.InvalidEmployeeAreaNameException;

public final class EmployeeAreaFactory {

  private EmployeeAreaFactory() {
  }

  public static EmployeeArea createArea(String name) {
    String validatedName = validateName(name);

    return EmployeeArea.builder()
        .withContractId(UUID.randomUUID())
        .withAreaId(UUID.randomUUID())
        .withAreaName(validatedName)
        .withAssignmentDate(LocalDate.now())
        .build();
  }

  private static String validateName(String name) {
    if (name == null) {
      throw new InvalidEmployeeAreaNameException("Employee area name must not be null");
    }

    String trimmedName = name.trim();
    if (trimmedName.isEmpty()) {
      throw new InvalidEmployeeAreaNameException("Employee area name must not be blank");
    }

    if (trimmedName.length() < 3) {
      throw new InvalidEmployeeAreaNameException("Employee area name must have at least 3 characters");
    }

    return trimmedName;
  }
}
