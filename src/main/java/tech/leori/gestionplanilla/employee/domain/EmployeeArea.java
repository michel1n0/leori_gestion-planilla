package tech.leori.gestionplanilla.employee.domain;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public final class EmployeeArea {
  private final UUID contractId;
  private final UUID areaId;
  private final String areaName;
  private final LocalDate assignmentDate;

  private EmployeeArea(Builder builder) {
    this.contractId = builder.contractId;
    this.areaId = builder.areaId;
    this.areaName = builder.areaName;
    this.assignmentDate = builder.assignmentDate;
  }

  public UUID getContractId() {
    return contractId;
  }

  public UUID getAreaId() {
    return areaId;
  }

  public String getAreaName() {
    return areaName;
  }

  public LocalDate getAssignmentDate() {
    return assignmentDate;
  }

  public static Builder builder() {
    return new Builder();
  }

  public Builder toBuilder() {
    return new Builder()
        .withContractId(this.contractId)
        .withAreaId(this.areaId)
        .withAreaName(this.areaName)
        .withAssignmentDate(this.assignmentDate);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((contractId == null) ? 0 : contractId.hashCode());
    result = prime * result + ((areaId == null) ? 0 : areaId.hashCode());
    result = prime * result + ((areaName == null) ? 0 : areaName.hashCode());
    result = prime * result + ((assignmentDate == null) ? 0 : assignmentDate.hashCode());
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
    EmployeeArea other = (EmployeeArea) obj;
    if (contractId == null) {
      if (other.contractId != null)
        return false;
    } else if (!contractId.equals(other.contractId))
      return false;
    if (areaId == null) {
      if (other.areaId != null)
        return false;
    } else if (!areaId.equals(other.areaId))
      return false;
    if (areaName == null) {
      if (other.areaName != null)
        return false;
    } else if (!areaName.equals(other.areaName))
      return false;
    if (assignmentDate == null) {
      if (other.assignmentDate != null)
        return false;
    } else if (!assignmentDate.equals(other.assignmentDate))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "EmployeeArea [contractId=" + contractId + ", areaId=" + areaId + ", areaName=" + areaName
        + ", assignmentDate=" + assignmentDate + ", getContractId()=" + getContractId() + ", getAreaId()="
        + getAreaId() + ", getAreaName()=" + getAreaName() + ", getAssignmentDate()=" + getAssignmentDate()
        + ", toBuilder()=" + toBuilder() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
        + ", toString()=" + super.toString() + "]";
  }

  public static final class Builder {
    private UUID contractId;
    private UUID areaId;
    private String areaName;
    private LocalDate assignmentDate;

    private Builder() {
    }

    public Builder withContractId(UUID contractId) {
      this.contractId = contractId;
      return this;
    }

    public Builder withAreaId(UUID areaId) {
      this.areaId = areaId;
      return this;
    }

    public Builder withAreaName(String areaName) {
      this.areaName = areaName;
      return this;
    }

    public Builder withAssignmentDate(LocalDate assignmentDate) {
      this.assignmentDate = assignmentDate;
      return this;
    }

    public EmployeeArea build() {
      UUID validatedContractId = Objects.requireNonNull(contractId, "contractId must not be null");
      UUID validatedAreaId = Objects.requireNonNull(areaId, "areaId must not be null");
      String validatedAreaName = validateNonBlank(areaName, "areaName");
      LocalDate validatedAssignmentDate = Objects.requireNonNull(assignmentDate, "assignmentDate must not be null");

      this.contractId = validatedContractId;
      this.areaId = validatedAreaId;
      this.areaName = validatedAreaName;
      this.assignmentDate = validatedAssignmentDate;

      return new EmployeeArea(this);
    }

    private static String validateNonBlank(String value, String fieldName) {
      if (value == null || value.isBlank()) {
        throw new IllegalArgumentException(fieldName + " must not be null or blank");
      }
      return value;
    }
  }
}
