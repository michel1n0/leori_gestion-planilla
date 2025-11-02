package tech.leori.gestionplanilla.employees.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name = "document_type", nullable = false, length = 50)
    private String documentType;

    @Column(name = "document_number", nullable = false, length = 50, unique = true)
    private String documentNumber;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "client_id", nullable = false)
    private Long clientId;

    protected Employee() {
        // Required by JPA
    }

    private Employee(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.documentType = builder.documentType;
        this.documentNumber = builder.documentNumber;
        this.birthDate = builder.birthDate;
        this.clientId = builder.clientId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getId() {
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

    public Long getClientId() {
        return clientId;
    }

    public static final class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private String documentType;
        private String documentNumber;
        private LocalDate birthDate;
        private Long clientId;

        private Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder documentType(String documentType) {
            this.documentType = documentType;
            return this;
        }

        public Builder documentNumber(String documentNumber) {
            this.documentNumber = documentNumber;
            return this;
        }

        public Builder birthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Builder clientId(Long clientId) {
            this.clientId = clientId;
            return this;
        }

        public Employee build() {
            validate();
            sanitize();
            return new Employee(this);
        }

        private void validate() {
            if (!hasText(firstName)) {
                throw new IllegalArgumentException("First name is required");
            }
            if (!hasText(lastName)) {
                throw new IllegalArgumentException("Last name is required");
            }
            if (!hasText(documentType)) {
                throw new IllegalArgumentException("Document type is required");
            }
            if (!hasText(documentNumber)) {
                throw new IllegalArgumentException("Document number is required");
            }
            if (birthDate == null) {
                throw new IllegalArgumentException("Birth date is required");
            }
            if (birthDate.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("Birth date cannot be in the future");
            }
            if (clientId == null) {
                throw new IllegalArgumentException("Client id is required");
            }
        }

        private void sanitize() {
            firstName = firstName.trim();
            lastName = lastName.trim();
            documentType = documentType.trim();
            documentNumber = documentNumber.trim();
        }

        private boolean hasText(String value) {
            return value != null && !value.trim().isEmpty();
        }
    }
}

