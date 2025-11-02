package tech.leori.gestionplanilla.employee.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public final class EmployeeContract {
  private final UUID contractId;
  private final UUID employeeId;
  private final LocalDate startDate;
  private final LocalDate endDate;
  private final BigDecimal salary;
  private final String contractType;

  private EmployeeContract(Builder builder) {
    this.contractId = builder.contractId;
    this.employeeId = builder.employeeId;
    this.startDate = builder.startDate;
    this.endDate = builder.endDate;
    this.salary = builder.salary;
    this.contractType = builder.contractType;
  }

  public UUID getContractId() {
    return contractId;
  }

  public UUID getEmployeeId() {
    return employeeId;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public BigDecimal getSalary() {
    return salary;
  }

  public String getContractType() {
    return contractType;
  }

  public static Builder builder() {
    return new Builder();
  }

  public Builder toBuilder() {
    return new Builder()
        .withContractId(this.contractId)
        .withEmployeeId(this.employeeId)
        .withStartDate(this.startDate)
        .withEndDate(this.endDate)
        .withSalary(this.salary)
        .withContractType(this.contractType);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((contractId == null) ? 0 : contractId.hashCode());
    result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
    result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
    result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
    result = prime * result + ((salary == null) ? 0 : salary.hashCode());
    result = prime * result + ((contractType == null) ? 0 : contractType.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    EmployeeContract other = (EmployeeContract) obj;
    if (contractId == null) {
      if (other.contractId != null)
        return false;
    } else if (!contractId.equals(other.contractId))
      return false;
    if (employeeId == null) {
      if (other.employeeId != null)
        return false;
    } else if (!employeeId.equals(other.employeeId))
      return false;
    if (startDate == null) {
      if (other.startDate != null)
        return false;
    } else if (!startDate.equals(other.startDate))
      return false;
    if (endDate == null) {
      if (other.endDate != null)
        return false;
    } else if (!endDate.equals(other.endDate))
      return false;
    if (salary == null) {
      if (other.salary != null)
        return false;
    } else if (!salary.equals(other.salary))
      return false;
    if (contractType == null) {
      if (other.contractType != null)
        return false;
    } else if (!contractType.equals(other.contractType))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "EmployeeContract [contractId=" + contractId + ", employeeId=" + employeeId + ", startDate=" + startDate
        + ", endDate=" + endDate + ", salary=" + salary + ", contractType=" + contractType + ", getContractId()="
        + getContractId() + ", getEmployeeId()=" + getEmployeeId() + ", getStartDate()=" + getStartDate()
        + ", getEndDate()=" + getEndDate() + ", getSalary()=" + getSalary() + ", getContractType()="
        + getContractType() + ", toBuilder()=" + toBuilder() + ", getClass()=" + getClass() + ", hashCode()="
        + hashCode() + ", toString()=" + super.toString() + "]";
  }

  public static final class Builder {
    private UUID contractId;
    private UUID employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal salary;
    private String contractType;

    private Builder() {
    }

    public Builder withContractId(UUID contractId) {
      this.contractId = contractId;
      return this;
    }

    public Builder withEmployeeId(UUID employeeId) {
      this.employeeId = employeeId;
      return this;
    }

    public Builder withStartDate(LocalDate startDate) {
      this.startDate = startDate;
      return this;
    }

    public Builder withEndDate(LocalDate endDate) {
      this.endDate = endDate;
      return this;
    }

    public Builder withSalary(BigDecimal salary) {
      this.salary = salary;
      return this;
    }

    public Builder withContractType(String contractType) {
      this.contractType = contractType;
      return this;
    }

    public EmployeeContract build() {
      UUID validatedContractId = Objects.requireNonNullElseGet(contractId, UUID::randomUUID);
      UUID validatedEmployeeId = Objects.requireNonNull(employeeId, "employeeId must not be null");
      LocalDate validatedStartDate = Objects.requireNonNull(startDate, "startDate must not be null");
      LocalDate validatedEndDate = validateEndDate(validatedStartDate, endDate);
      BigDecimal validatedSalary = Objects.requireNonNull(salary, "salary must not be null");
      String validatedContractType = validateNonBlank(contractType, "contractType");

      this.contractId = validatedContractId;
      this.employeeId = validatedEmployeeId;
      this.startDate = validatedStartDate;
      this.endDate = validatedEndDate;
      this.salary = validatedSalary;
      this.contractType = validatedContractType;

      return new EmployeeContract(this);
    }

    private static LocalDate validateEndDate(LocalDate startDate, LocalDate endDate) {
      if (endDate != null && endDate.isBefore(startDate)) {
        throw new IllegalArgumentException("endDate must not be before startDate");
      }
      return endDate;
    }

    private static String validateNonBlank(String value, String fieldName) {
      if (value == null || value.isBlank()) {
        throw new IllegalArgumentException(fieldName + " must not be null or blank");
      }
      return value;
    }
  }
}
