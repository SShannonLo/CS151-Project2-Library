import java.util.HashMap;
import java.util.ArrayList;

public class Library{ 
	
	// These fields are for ease of adjustments. They can be hardcoded instead if needed.
	private String libraryName;
	private String libraryAddress;
	private String openTime;
	private String closeTime;
	private String weekendHours;
	
	private int maxUsers;
	private int maxBooks;
	
	private HashMap<String, LibraryUser> users; // includes both Member and Staff
	private HashMap<String, Book> books; // All books are differentiated by ISBN# 
	
	// Library constructor. We will only have one Librray
	public Library(String name, String address, String openTime, String closeTime, 
			String weekendHours, int maxUsers, int maxBooks) {
		
		// General information about the library.
		this.libraryName = name;
        this.libraryAddress = address;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.weekendHours = weekendHours;
        this.maxUsers = maxUsers;
        this.maxBooks = maxBooks;
		
		// Library has a hashmap of users and a hashmap of books.
		users = new HashMap<>();
		books = new HashMap<>();
		
	}
	
	public String activeHours() {
		return "Weekdays: " + openTime + " - " + closeTime + ", Weekends: " + weekendHours;
	}
	
	// Add user to the HashMap of all library users
	public void addUser(LibraryUser user) {
		users.put(user.getId(), user); // to the HashMap add 

    }
	
	public boolean removeUser(String id) {
	    return users.remove(id) != null;
	}
	
	// Authentication procedure
	public LibraryUser login(String id, String pin) {
	    LibraryUser user = users.get(id);

	    if (user != null && user.getPin().equals(pin)) {
	        return user;
	    }

	    return null;
	}
	
	// Add book to the HashMap of all books
	public void addBook(Book book) {
		books.put(book.getIsbn(), book); // Use isbn as the hashmap KEY as well
		
	}
	
	// Getter for the book of interest
	public Book getBook(String isbn) {
		return books.get(isbn);
	}
	
	public void showAllBooks() {
		// For-each loop
		for(Book book : books.values()) {
			// The Book class should implement a toString method to print book details for all books.
			System.out.println(book);
		}
	}

	
	
}






