import java.util.HashMap;

public class Library {
	private HashMap<Integer, LibraryUser> users; // includes both Member and Staff
	private HashMap<Integer, Book> books;
	
	// Constructor
	public Library() {
		// library has a hashmap of users and a hashmap of books
		users = new HashMap<>();
		books = new HashMap<>();
		
	}
	
	// Add user to the HashMap of all library users
	public void addUser(LibraryUser user) {
		users.put(user.getId(), user); // to the HashMap add 


    }
}
