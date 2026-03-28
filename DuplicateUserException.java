
// thrown when a user already exists in the library system
public class DuplicateUserException extends Exception {
    // required for serialization
    private static final long serialVersionUID = 1L;

    public DuplicateUserException(String message) {
        super(message);
    }
}