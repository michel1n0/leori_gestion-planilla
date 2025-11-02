package tech.leori.gestionplanilla.shared.domain.exception;

public class DomainException extends RuntimeException {

  private final Enum<?> error;

  public DomainException(String message) {
    super(message);
    this.error = null;
  }

  public DomainException(Enum<?> error) {
    super(error != null ? error.name() : null);
    this.error = error;
  }

  public Enum<?> getError() {
    return error;
  }
}
