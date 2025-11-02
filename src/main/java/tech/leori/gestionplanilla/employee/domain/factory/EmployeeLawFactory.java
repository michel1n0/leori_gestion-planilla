package tech.leori.gestionplanilla.employee.domain.factory;

import java.util.regex.Pattern;

import tech.leori.gestionplanilla.employee.domain.EmployeeLaw;
import tech.leori.gestionplanilla.employee.domain.exception.InvalidEmployeeLawException;

public final class EmployeeLawFactory {

  private static final Pattern CODE_PATTERN = Pattern.compile("[A-Z]{2,5}");

  private EmployeeLawFactory() {
  }

  public static EmployeeLaw createLaw(String code, String description) {
    String validatedCode = validateCode(code);
    String validatedDescription = validateDescription(description);

    return new EmployeeLaw(validatedCode, validatedDescription);
  }

  private static String validateCode(String code) {
    if (code == null) {
      throw new InvalidEmployeeLawException("Employee law code must not be null");
    }

    String trimmedCode = code.trim();
    if (trimmedCode.isEmpty()) {
      throw new InvalidEmployeeLawException("Employee law code must not be blank");
    }

    if (!CODE_PATTERN.matcher(trimmedCode).matches()) {
      throw new InvalidEmployeeLawException("Employee law code must contain 2 to 5 uppercase letters");
    }

    return trimmedCode;
  }

  private static String validateDescription(String description) {
    if (description == null) {
      throw new InvalidEmployeeLawException("Employee law description must not be null");
    }

    String trimmedDescription = description.trim();
    if (trimmedDescription.isEmpty()) {
      throw new InvalidEmployeeLawException("Employee law description must not be blank");
    }

    return trimmedDescription;
  }
}
