package cursach.exceptions;

public class NoUserLogIn extends RuntimeException {
    public NoUserLogIn(String message) {
        super(message);
    }
}
