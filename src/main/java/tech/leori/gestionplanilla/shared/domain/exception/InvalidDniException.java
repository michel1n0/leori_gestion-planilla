package tech.leori.gestionplanilla.shared.domain.exception;

public class InvalidDniException extends RuntimeException {
    public InvalidDniException(String message) {
        super(message);
    }
}
