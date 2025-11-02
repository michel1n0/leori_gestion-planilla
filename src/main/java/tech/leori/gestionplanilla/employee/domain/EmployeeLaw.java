package tech.leori.gestionplanilla.employee.domain;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public final class EmployeeLaw {
  private final UUID contractId;
  private final UUID lawRegimeId;
  private final String regimeName;
  private final LocalDate registrationDate;

  private EmployeeLaw(Builder builder) {
    this.contractId = builder.contractId;
    this.lawRegimeId = builder.lawRegimeId;
    this.regimeName = builder.regimeName;
    this.registrationDate = builder.registrationDate;
  }

  public UUID getContractId() {
    return contractId;
  }

  public UUID getLawRegimeId() {
    return lawRegimeId;
  }

  public String getRegimeName() {
    return regimeName;
  }

  public LocalDate getRegistrationDate() {
    return registrationDate;
  }

  public static Builder builder() {
    return new Builder();
  }

  public Builder toBuilder() {
    return new Builder()
        .withContractId(this.contractId)
        .withLawRegimeId(this.lawRegimeId)
        .withRegimeName(this.regimeName)
        .withRegistrationDate(this.registrationDate);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((contractId == null) ? 0 : contractId.hashCode());
    result = prime * result + ((lawRegimeId == null) ? 0 : lawRegimeId.hashCode());
    result = prime * result + ((regimeName == null) ? 0 : regimeName.hashCode());
    result = prime * result + ((registrationDate == null) ? 0 : registrationDate.hashCode());
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
    EmployeeLaw other = (EmployeeLaw) obj;
    if (contractId == null) {
      if (other.contractId != null)
        return false;
    } else if (!contractId.equals(other.contractId))
      return false;
    if (lawRegimeId == null) {
      if (other.lawRegimeId != null)
        return false;
    } else if (!lawRegimeId.equals(other.lawRegimeId))
      return false;
    if (regimeName == null) {
      if (other.regimeName != null)
        return false;
    } else if (!regimeName.equals(other.regimeName))
      return false;
    if (registrationDate == null) {
      if (other.registrationDate != null)
        return false;
    } else if (!registrationDate.equals(other.registrationDate))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "EmployeeLaw [contractId=" + contractId + ", lawRegimeId=" + lawRegimeId + ", regimeName=" + regimeName
        + ", registrationDate=" + registrationDate + ", getContractId()=" + getContractId()
        + ", getLawRegimeId()=" + getLawRegimeId() + ", getRegimeName()=" + getRegimeName()
        + ", getRegistrationDate()=" + getRegistrationDate() + ", toBuilder()=" + toBuilder() + ", getClass()="
        + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
  }

  public static final class Builder {
    private UUID contractId;
    private UUID lawRegimeId;
    private String regimeName;
    private LocalDate registrationDate;

    private Builder() {
    }

    public Builder withContractId(UUID contractId) {
      this.contractId = contractId;
      return this;
    }

    public Builder withLawRegimeId(UUID lawRegimeId) {
      this.lawRegimeId = lawRegimeId;
      return this;
    }

    public Builder withRegimeName(String regimeName) {
      this.regimeName = regimeName;
      return this;
    }

    public Builder withRegistrationDate(LocalDate registrationDate) {
      this.registrationDate = registrationDate;
      return this;
    }

    public EmployeeLaw build() {
      UUID validatedContractId = Objects.requireNonNull(contractId, "contractId must not be null");
      UUID validatedLawRegimeId = Objects.requireNonNull(lawRegimeId, "lawRegimeId must not be null");
      String validatedRegimeName = validateNonBlank(regimeName, "regimeName");
      LocalDate validatedRegistrationDate = Objects.requireNonNull(registrationDate,
          "registrationDate must not be null");

      this.contractId = validatedContractId;
      this.lawRegimeId = validatedLawRegimeId;
      this.regimeName = validatedRegimeName;
      this.registrationDate = validatedRegistrationDate;

      return new EmployeeLaw(this);
    }

    private static String validateNonBlank(String value, String fieldName) {
      if (value == null || value.isBlank()) {
        throw new IllegalArgumentException(fieldName + " must not be null or blank");
      }
      return value;
    }
  }
}
