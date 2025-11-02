package tech.leori.gestionplanilla.client.domain;

import java.util.Objects;
import java.util.UUID;

public final class Client {
  private final UUID id;
  private final String businessName;
  private final String ruc;
  private final String industryType;

  private Client(Builder builder) {
    this.id = builder.id;
    this.businessName = builder.businessName;
    this.ruc = builder.ruc;
    this.industryType = builder.industryType;
  }

  public UUID getId() {
    return id;
  }

  public String getBusinessName() {
    return businessName;
  }

  public String getRuc() {
    return ruc;
  }

  public String getIndustryType() {
    return industryType;
  }

  public static Builder builder() {
    return new Builder();
  }

  public Builder toBuilder() {
    return new Builder()
            .withId(this.id)
            .withBussinessName(this.businessName)
            .withRuc(this.ruc)
            .withIndustryType(this.industryType);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((businessName == null) ? 0 : businessName.hashCode());
    result = prime * result + ((ruc == null) ? 0 : ruc.hashCode());
    result = prime * result + ((industryType == null) ? 0 : industryType.hashCode());
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
    Client other = (Client) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (businessName == null) {
      if (other.businessName != null)
        return false;
    } else if (!businessName.equals(other.businessName))
      return false;
    if (ruc == null) {
      if (other.ruc != null)
        return false;
    } else if (!ruc.equals(other.ruc))
      return false;
    if (industryType == null) {
      if (other.industryType != null)
        return false;
    } else if (!industryType.equals(other.industryType))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Client [id=" + id + ", businessName=" + businessName + ", ruc=" + ruc + ", industryType=" + industryType
        + ", getId()=" + getId() + ", getBusinessName()=" + getBusinessName() + ", getRuc()=" + getRuc()
        + ", getIndustryType()=" + getIndustryType() + ", toBuilder()=" + toBuilder() + ", getClass()=" + getClass()
        + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
  }

  public static final class Builder {
    private UUID id;
    private String businessName;
    private String ruc;
    private String industryType;

    private Builder() {
    }

    public Builder withId(UUID id) {
      this.id = id;
      return this;
    }

    public Builder withBussinessName(String businessName) {
      this.businessName = businessName;
      return this;
    }

    public Builder withRuc(String ruc) {
      this.ruc = ruc;
      return this;
    }

    public Builder withIndustryType(String industryType) {
      this.industryType = industryType;
      return this;
    }

    public Client build() {
      UUID validatedId = Objects.requireNonNullElseGet(id, UUID::randomUUID);
      String validatedBusinessName = validateNonBlank(businessName, "businessName");
      String validatedRuc = validateNonBlank(ruc, "ruc");
      String validatedIndustryType = validateNonBlank(industryType, "industryType");

      this.id = validatedId;
      this.businessName = validatedBusinessName;
      this.ruc = validatedRuc;
      this.industryType = validatedIndustryType;

      return new Client(this);
    }

    private static String validateNonBlank(String value, String fieldName) {
      if(value == null || value.isBlank()) {
        throw new IllegalArgumentException(fieldName + "must not be null or blank");
      }
      return value;
    }
  }
}
