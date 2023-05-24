package safePay.exceptions;

public class InvalidStatementException extends RuntimeException{
    public InvalidStatementException(String message){
        super(message);
    }
}
