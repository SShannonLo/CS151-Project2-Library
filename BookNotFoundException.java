// thrown when a book cannot be found in the library
public class BookNotFoundException extends Exception {
    // required for serialization
    private static final long serialVersionUID = 1L;

    public BookNotFoundException(String message) {
        super(message);
    }
}