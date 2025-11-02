package tech.leori.gestionplanilla.client.application.exception;

import tech.leori.gestionplanilla.shared.domain.exception.DomainException;

public class ClientException extends DomainException {

  public ClientException(String message) {
    super(message);
  }

  public ClientException(Enum<?> error) {
    super(error);
  }
}
