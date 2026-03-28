
// thrown when a member cannot be found
public class MemberNotFoundException extends Exception {
    // required for serialization
    private static final long serialVersionUID = 1L;

    public MemberNotFoundException(String message) {
        super(message);
    }
}