
// thrown when a pin is null or empty
public class InvalidPinException extends Exception {
    public InvalidPinException(String message) {
        super(message);
    }
}