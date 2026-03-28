// thrown when a pin is null or empty
public class InvalidPinException extends Exception {
    // required for serialization
    private static final long serialVersionUID = 1L;

    public InvalidPinException(String message) {
        super(message);
    }
}