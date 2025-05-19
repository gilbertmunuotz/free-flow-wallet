package free_flow_wallet.backend.exceptions;

public class WrongCredentials extends RuntimeException {
    public WrongCredentials(String message) {
        super(message);
    }
}
