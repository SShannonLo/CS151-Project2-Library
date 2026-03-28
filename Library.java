import java.util.HashMap;

public class Library {

	// Library specifics. We can add more libraries, if we want to in the future.
    private String libraryName;
    private String libraryAddress;
    private String openTime;
    private String closeTime;
    private String weekendHours;

    // These are assigned to be 100 when Library object is created in Main.
    private int maxUsers;
    private int maxBooks;

    // We keep all users in one HashMap, since all of them are instances of LibraryUser
    private HashMap<String, LibraryUser> users;
    
    // The books are handled by Catalog class (ArrayList)
    private Catalog catalog;

    // Constructor to create one Library object
    public Library(String name, String address, String openTime, String closeTime,
                   String weekendHours, int maxUsers, int maxBooks) {
        this.libraryName = name;
        this.libraryAddress = address;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.weekendHours = weekendHours;
        this.maxUsers = maxUsers;
        this.maxBooks = maxBooks;

        // Each library has its own users. 
        this.users = new HashMap<>(); 
        
        // Books are stored in Catalog
        this.catalog = new Catalog("Main Catalog");
    }

    // This method prints the library hours. Printed separately for ease of adjustment of needed
    public String activeHours() {
        return "Weekdays: " + openTime + " - " + closeTime + ", Weekends: " + weekendHours;
    }

    // This method deals with the request to add a new user object to the hashMap of users
    public void addUser(LibraryUser user) throws DuplicateUserException {
    	
    	// Ensuring no one passes a null user. Shouldn't happen, but we are staying safe.
        if (user == null) {
            System.out.println("Cannot add a null user.");
            return;
        }

        // Speck has max size for the book database, so we added max user count per library for practice.
        if (users.size() >= maxUsers) {
            System.out.println("Maximum user limit reached.");
            return;
        }

        // Two users cannot have the same ID.
        if (users.containsKey(user.getId())) {
        	
            System.out.println("User with this ID already exists.");
            throw new DuplicateUserException("User with this ID already exists.");
            
        }

        // Add newly created user to the hashMap users.
        users.put(user.getId(), user);
    }

    // Removes user from the hashMap only if it exists.
    public boolean removeUser(String id) {
    	
    	// Auto-evaluates to T or F
        return users.remove(id) != null;
    }

    // User log-n method.
    public LibraryUser login(String id, String pin) {
    	
    	// Create reference of LibraryUser user to everything from the user that was passed in the param.
        LibraryUser user = users.get(id);

        // Make sure it exists and compare pins with .equals(), since pins are Strings.
        if (user != null && user.getPin().equals(pin)) {
            return user;
        }

        // Authentication failed.
        return null;
    }

    // Books are added to the library class instances.
    public boolean addBook(Book book) {
        if (book == null) {
            System.out.println("Cannot add a null book.");
            return false;
        }

        // Speck required limiting the number of books that can be added.
        if (catalog.getBooks().size() >= maxBooks) {
            System.out.println("Maximum book limit reached.");
            return false;
        }

        // Catalog handles the book storage.
        return catalog.addBook(book);
    }

    // Helper method.
    public boolean removeBook(Book book) {
        return catalog.removeBook(book);
    }

    // Helper method.
    public Book getBook(String bookId) {
        return catalog.searchByBookId(bookId);
    }
    
    // Helper method.
    public void showAllBooks() {
        catalog.displayAllBooks();
    }
    
    // You can search books by title too.
    public void searchBooksByTitle(String title) {
    	
    	// Take care of the white space.
        if (title == null || title.trim().isEmpty()) {
            System.out.println("Invalid title.");
            return;
        }

        // Compiler figures out the type. Very similar to "auto" in C++.
        // Many books can have the same title.
        var results = catalog.searchByTitle(title);

        if (results.isEmpty()) {
            System.out.println("No books found with that title.");
            return;
        }

        // For each loop. Goes through all objects in results, 
        //since there can by many books with the same title.
        for (Book book : results) {
            System.out.println(book);
        }
    }

    // This method prints books for any specific author.
    public void searchBooksByAuthor(String author) {
    	
    	// Take care of the white spaces.
        if (author == null || author.trim().isEmpty()) {
            System.out.println("Invalid author.");
            return;
        }

        // This is the "list" of all matching authors.
        var results = catalog.searchByAuthor(author);

        if (results.isEmpty()) {
            System.out.println("No books found by that author.");
            return;
        }

        // Print all books by that author.
        for (Book book : results) {
            System.out.println(book);
        }
    }

    // Helper.
    public String getLibraryName() {
        return libraryName;
    }

    // Helper.
    public String getLibraryAddress() {
        return libraryAddress;
    }

    // Helper.
    public Catalog getCatalog() {
        return catalog;
    }

    // Helper.
    public HashMap<String, LibraryUser> getUsers() {
        return users;
    }

    // This is how you print the library info for any particular library.
    public String toString() {
        return "Library Name: " + libraryName +
               "\nAddress: " + libraryAddress +
               "\nHours: " + activeHours() +
               "\nUsers Registered: " + users.size() +
               "\nBooks in Catalog: " + catalog.getBooks().size();
    }
}



