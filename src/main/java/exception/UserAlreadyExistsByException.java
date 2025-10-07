package exception;

public class UserAlreadyExistsByException extends RuntimeException {
    public UserAlreadyExistsByException(String message) {
        super(message);
    }
}
