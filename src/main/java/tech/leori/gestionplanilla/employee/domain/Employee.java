package tech.leori.gestionplanilla.employee.domain;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public final class Employee {
  private final UUID id;
  private final String firstName;
  private final String lastName;
  private final String documentType;
  private final String documentNumber;
  private final LocalDate birthDate;
  private final UUID clientId;

  Employee(
      UUID id,
      String firstName,
      String lastName,
      String documentType,
      String documentNumber,
      LocalDate birthDate,
      UUID clientId) {
    this.id = Objects.requireNonNullElseGet(id, UUID::randomUUID);
    this.firstName = validateNonBlank(firstName, "firstName");
    this.lastName = validateNonBlank(lastName, "lastName");
    this.documentType = validateNonBlank(documentType, "documentType");
    this.documentNumber = validateNonBlank(documentNumber, "documentNumber");
    this.birthDate = validateBirthDate(birthDate);
    this.clientId = Objects.requireNonNull(clientId, "clientId must not be null");
  }

  public UUID getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getDocumentType() {
    return documentType;
  }

  public String getDocumentNumber() {
    return documentNumber;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public UUID getClientId() {
    return clientId;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
    result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
    result = prime * result + ((documentType == null) ? 0 : documentType.hashCode());
    result = prime * result + ((documentNumber == null) ? 0 : documentNumber.hashCode());
    result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
    result = prime * result + ((clientId == null) ? 0 : clientId.hashCode());
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
    Employee other = (Employee) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (firstName == null) {
      if (other.firstName != null)
        return false;
    } else if (!firstName.equals(other.firstName))
      return false;
    if (lastName == null) {
      if (other.lastName != null)
        return false;
    } else if (!lastName.equals(other.lastName))
      return false;
    if (documentType == null) {
      if (other.documentType != null)
        return false;
    } else if (!documentType.equals(other.documentType))
      return false;
    if (documentNumber == null) {
      if (other.documentNumber != null)
        return false;
    } else if (!documentNumber.equals(other.documentNumber))
      return false;
    if (birthDate == null) {
      if (other.birthDate != null)
        return false;
    } else if (!birthDate.equals(other.birthDate))
      return false;
    if (clientId == null) {
      if (other.clientId != null)
        return false;
    } else if (!clientId.equals(other.clientId))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", documentType="
        + documentType + ", documentNumber=" + documentNumber + ", birthDate=" + birthDate + ", clientId="
        + clientId + "]";
  }

  private static String validateNonBlank(String value, String fieldName) {
    if (value == null || value.isBlank()) {
      throw new IllegalArgumentException(fieldName + " must not be null or blank");
    }
    return value;
  }

  private static LocalDate validateBirthDate(LocalDate birthDate) {
    if (birthDate == null) {
      throw new IllegalArgumentException("birthDate must not be null");
    }
    if (birthDate.isAfter(LocalDate.now())) {
      throw new IllegalArgumentException("birthDate must be in the past");
    }
    return birthDate;
  }
}
