import java.util.HashMap;

public class Library extends LibraryUser{ // extends the abstract class LibraryUser
	private HashMap<Integer, LibraryUser> users; // includes both Member and Staff
	private HashMap<String, Book> books; // All books are differentiated by ISBN# 
	
	// Library constructor. We will only have one Librray
	public Library() {
		// library has a hashmap of users and a hashmap of books
		users = new HashMap<>();
		books = new HashMap<>();
		
	}
	
	// Add user to the HashMap of all library users
	public void addUser(LibraryUser user) {
		users.put(user.getId(), user); // to the HashMap add 

    }
	
	// Authentication procedure
	public LibraryUser login (int id, int pin) {
		LibraryUser user = users.get(id);
		
		// If the user exists (avoids errors) and entered correct credentials, return their info
		if(user != null && user.authenticate(pin)) {
			return user;
		}
		
		return null; // In case auth failed
	}
	
	// Add book to the HashMap of all books
	public void addBook(Book book) {
		books.put(book.getIsbn(), book); // Use isbn as the hashmap KEY as well
		
	}
	
	// Getter for the book of interest
	public Book getBook(String isbn) {
		return books.get(isbn);
	}
	
	
}
