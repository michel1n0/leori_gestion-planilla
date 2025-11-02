package tech.leori.gestionplanilla.employee.domain;

import java.util.Objects;

public final class EmployeeLaw {

  private final String code;
  private final String description;

  public EmployeeLaw(String code, String description) {
    this.code = Objects.requireNonNull(code, "code must not be null");
    this.description = Objects.requireNonNull(description, "description must not be null");
  }

  public String getCode() {
    return code;
  }

  public String getDescription() {
    return description;
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
    return Objects.equals(code, other.code) && Objects.equals(description, other.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, description);
  }

  @Override
  public String toString() {
    return "EmployeeLaw{" +
        "code='" + code + '\'' +
        ", description='" + description + '\'' +
        '}';
  }
}
