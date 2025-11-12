package tech.leori.gestionplanilla.employee.domain;

import java.math.BigDecimal;
import java.util.Objects;

public final class EmployeeLaw {

  private final String code;
  private final String description;
  private final BigDecimal contributionPercentage;

  public EmployeeLaw(String code, String description, BigDecimal contributionPercentage) {
    this.code = Objects.requireNonNull(code, "code must not be null");
    this.description = Objects.requireNonNull(description, "description must not be null");
    this.contributionPercentage =
        Objects.requireNonNull(contributionPercentage, "contributionPercentage must not be null");
  }

  public String getCode() {
    return code;
  }

  public String getDescription() {
    return description;
  }

  public BigDecimal getContributionPercentage() {
    return contributionPercentage;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    EmployeeLaw other = (EmployeeLaw) obj;
    return Objects.equals(code, other.code)
        && Objects.equals(description, other.description)
        && Objects.equals(contributionPercentage, other.contributionPercentage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, description, contributionPercentage);
  }

  @Override
  public String toString() {
    return "EmployeeLaw{" +
        "code='" + code + '\'' +
        ", description='" + description + '\'' +
        ", contributionPercentage=" + contributionPercentage +
        '}';
  }
}
