package safePay.exceptions;

public class DatabaseConnectionFailedException extends RuntimeException{
    public DatabaseConnectionFailedException(String message) {
        super(message);
    }
}
