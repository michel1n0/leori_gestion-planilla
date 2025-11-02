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

  private Employee(Builder builder) {
    this.id = builder.id;
    this.firstName = builder.firstName;
    this.lastName = builder.lastName;
    this.documentType = builder.documentType;
    this.documentNumber = builder.documentNumber;
    this.birthDate = builder.birthDate;
    this.clientId = builder.clientId;
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

  public static Builder builder() {
    return new Builder();
  }

  public Builder toBuilder() {
    return new Builder()
        .withId(this.id)
        .withFirstName(this.firstName)
        .withLastName(this.lastName)
        .withDocumentType(this.documentType)
        .withDocumentNumber(this.documentNumber)
        .withBirthDate(this.birthDate)
        .withClientId(this.clientId);
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
        + clientId + ", getId()=" + getId() + ", getFirstName()=" + getFirstName() + ", getLastName()="
        + getLastName() + ", getDocumentType()=" + getDocumentType() + ", getDocumentNumber()="
        + getDocumentNumber() + ", getBirthDate()=" + getBirthDate() + ", getClientId()=" + getClientId()
        + ", toBuilder()=" + toBuilder() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
        + ", toString()=" + super.toString() + "]";
  }

  public static final class Builder {
    private UUID id;
    private String firstName;
    private String lastName;
    private String documentType;
    private String documentNumber;
    private LocalDate birthDate;
    private UUID clientId;

    private Builder() {
    }

    public Builder withId(UUID id) {
      this.id = id;
      return this;
    }

    public Builder withFirstName(String firstName) {
      this.firstName = firstName;
      return this;
    }

    public Builder withLastName(String lastName) {
      this.lastName = lastName;
      return this;
    }

    public Builder withDocumentType(String documentType) {
      this.documentType = documentType;
      return this;
    }

    public Builder withDocumentNumber(String documentNumber) {
      this.documentNumber = documentNumber;
      return this;
    }

    public Builder withBirthDate(LocalDate birthDate) {
      this.birthDate = birthDate;
      return this;
    }

    public Builder withClientId(UUID clientId) {
      this.clientId = clientId;
      return this;
    }

    public Employee build() {
      UUID validatedId = Objects.requireNonNullElseGet(id, UUID::randomUUID);
      String validatedFirstName = validateNonBlank(firstName, "firstName");
      String validatedLastName = validateNonBlank(lastName, "lastName");
      String validatedDocumentType = validateNonBlank(documentType, "documentType");
      String validatedDocumentNumber = validateNonBlank(documentNumber, "documentNumber");
      LocalDate validatedBirthDate = validateBirthDate(birthDate);
      UUID validatedClientId = Objects.requireNonNull(clientId, "clientId must not be null");

      this.id = validatedId;
      this.firstName = validatedFirstName;
      this.lastName = validatedLastName;
      this.documentType = validatedDocumentType;
      this.documentNumber = validatedDocumentNumber;
      this.birthDate = validatedBirthDate;
      this.clientId = validatedClientId;

      return new Employee(this);
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
}
