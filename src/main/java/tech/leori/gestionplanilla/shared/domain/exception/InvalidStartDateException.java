package tech.leori.gestionplanilla.shared.domain.exception;

public class InvalidStartDateException extends RuntimeException {
    public InvalidStartDateException(String message) {
        super(message);
    }
}
