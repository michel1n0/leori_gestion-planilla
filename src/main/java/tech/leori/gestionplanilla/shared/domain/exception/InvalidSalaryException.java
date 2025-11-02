package tech.leori.gestionplanilla.shared.domain.exception;

public class InvalidSalaryException extends RuntimeException {
    public InvalidSalaryException(String message) {
        super(message);
    }
}
