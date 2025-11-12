package tech.leori.gestionplanilla.employee.domain.factory;

import java.util.regex.Pattern;

import java.math.BigDecimal;

import tech.leori.gestionplanilla.employee.domain.EmployeeLaw;
import tech.leori.gestionplanilla.employee.domain.exception.InvalidEmployeeLawException;

public final class EmployeeLawFactory {

  private static final Pattern CODE_PATTERN = Pattern.compile("[A-Z]{2,5}");

  private EmployeeLawFactory() {
  }

  public static EmployeeLaw createLaw(String code, String description, BigDecimal contribution) {
    String validatedCode = validateCode(code);
    String validatedDescription = validateDescription(description);
    BigDecimal validatedContribution = validateContribution(contribution);

    return new EmployeeLaw(validatedCode, validatedDescription, validatedContribution);
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

  private static BigDecimal validateContribution(BigDecimal contribution) {
    if (contribution == null) {
      throw new InvalidEmployeeLawException("Employee law contribution must not be null");
    }

    if (contribution.signum() < 0) {
      throw new InvalidEmployeeLawException("Employee law contribution must not be negative");
    }

    return contribution;
  }
}
