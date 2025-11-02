package tech.leori.gestionplanilla.employee.domain.factory;

import java.time.LocalDate;
import java.util.UUID;
import java.util.regex.Pattern;

import tech.leori.gestionplanilla.employee.domain.Employee;
import tech.leori.gestionplanilla.employee.domain.exception.EmployeeDomainException;

public final class EmployeeFactory {

  private static final Pattern DNI_PATTERN = Pattern.compile("\\d{8}");
  private static final String DEFAULT_DOCUMENT_TYPE = "DNI";
  private static final long DEFAULT_AGE = 18L;

  private EmployeeFactory() {
  }

  public static Employee createEmployee(String dni, String fullName) {
    String validatedDni = validateDni(dni);
    String validatedFullName = validateFullName(fullName);

    String[] nameParts = validatedFullName.trim().split("\\s+", 2);
    String firstName = nameParts[0];
    String lastName = nameParts.length > 1 ? nameParts[1] : nameParts[0];

    return Employee.builder()
        .withFirstName(firstName)
        .withLastName(lastName)
        .withDocumentType(DEFAULT_DOCUMENT_TYPE)
        .withDocumentNumber(validatedDni)
        .withBirthDate(LocalDate.now().minusYears(DEFAULT_AGE))
        .withClientId(UUID.randomUUID())
        .build();
  }

  private static String validateDni(String dni) {
    if (dni == null || !DNI_PATTERN.matcher(dni).matches()) {
      throw new EmployeeDomainException("DNI must be exactly 8 numeric digits");
    }
    return dni;
  }

  private static String validateFullName(String fullName) {
    if (fullName == null || fullName.isBlank()) {
      throw new EmployeeDomainException("Full name must not be null or blank");
    }
    return fullName;
  }
}
