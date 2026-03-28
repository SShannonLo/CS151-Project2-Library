// Configuring the Junit.
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
	private Library library;
    private Member member1;
    private Member member2;
    private Book book1;
    private Book book2;
	
	@BeforeEach
    public void setUp() {
        library = new Library(
                "City Library",
                "123 Main St",
                "9:00 AM",
                "6:00 PM",
                "10:00 AM - 4:00 PM",
                100,
                100
        );

        member1 = new Member(
                "Angelina",
                "018134168",
                "angelina.ryabechenkova@sjsu.edu",
                "4254290000",
                "00",
                library
        );

        member2 = new Member(
                "David",
                "018134169",
                "david.doe@sjsu.edu",
                "4254291111",
                "11",
                library
        );

        book1 = new Book("B1", "1984", "George Orwell", "9780451524935");
        book2 = new Book("B2", "Pride and Prejudice", "Jane Austen", "9780141439518");
    }
	
	// First test is to see if we can add the user successfully.
	@Test
    public void testAddUser() throws Library.DuplicateUserException {
        library.addUser(member1);
        assertEquals(1, library.getUsers().size());
    }
	
	// Testing the custom exception that is in the Library class.
	@Test
    public void testDuplicateUserException() throws Library.DuplicateUserException {
        library.addUser(member1);

        assertThrows(
                Library.DuplicateUserException.class,
                () -> library.addUser(member1)
        );
    }
	
	// Testing login
	@Test
    public void testLoginSuccess() throws Library.DuplicateUserException {
        library.addUser(member1);

        LibraryUser user = library.login("018134168", "00");

        assertNotNull(user);
        assertEquals("Angelina", user.getName());
    }

}
