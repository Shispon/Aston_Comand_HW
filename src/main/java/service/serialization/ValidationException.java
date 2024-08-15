package service.serialization;

public class ValidationException extends Exception {
    private final String message;

    public ValidationException(String message) {
        this.message = message;
    }
}
