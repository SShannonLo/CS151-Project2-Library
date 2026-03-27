import java.util.HashMap;

public class Library {

    private String libraryName;
    private String libraryAddress;
    private String openTime;
    private String closeTime;
    private String weekendHours;

    private int maxUsers;
    private int maxBooks;

    private HashMap<String, LibraryUser> users;
    private Catalog catalog;

    public Library(String name, String address, String openTime, String closeTime,
                   String weekendHours, int maxUsers, int maxBooks) {
        this.libraryName = name;
        this.libraryAddress = address;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.weekendHours = weekendHours;
        this.maxUsers = maxUsers;
        this.maxBooks = maxBooks;

        this.users = new HashMap<>();
        this.catalog = new Catalog("Main Catalog");
    }

    public String activeHours() {
        return "Weekdays: " + openTime + " - " + closeTime + ", Weekends: " + weekendHours;
    }

    public void addUser(LibraryUser user) {
        if (user == null) {
            System.out.println("Cannot add a null user.");
            return;
        }

        if (users.size() >= maxUsers) {
            System.out.println("Maximum user limit reached.");
            return;
        }

        if (users.containsKey(user.getId())) {
            System.out.println("User with this ID already exists.");
            return;
        }

        users.put(user.getId(), user);
    }

    public boolean removeUser(String id) {
        return users.remove(id) != null;
    }

    public LibraryUser login(String id, String pin) {
        LibraryUser user = users.get(id);

        if (user != null && user.getPin().equals(pin)) {
            return user;
        }

        return null;
    }

    public boolean addBook(Book book) {
        if (book == null) {
            System.out.println("Cannot add a null book.");
            return false;
        }

        if (catalog.getBooks().size() >= maxBooks) {
            System.out.println("Maximum book limit reached.");
            return false;
        }

        return catalog.addBook(book);
    }

    public boolean removeBook(Book book) {
        return catalog.removeBook(book);
    }

    public Book getBook(String bookId) {
        return catalog.searchByBookId(bookId);
    }

    public void showAllBooks() {
        catalog.displayAllBooks();
    }

    public void searchBooksByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            System.out.println("Invalid title.");
            return;
        }

        var results = catalog.searchByTitle(title);

        if (results.isEmpty()) {
            System.out.println("No books found with that title.");
            return;
        }

        for (Book book : results) {
            System.out.println(book);
        }
    }

    public void searchBooksByAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            System.out.println("Invalid author.");
            return;
        }

        var results = catalog.searchByAuthor(author);

        if (results.isEmpty()) {
            System.out.println("No books found by that author.");
            return;
        }

        for (Book book : results) {
            System.out.println(book);
        }
    }

    public String getLibraryName() {
        return libraryName;
    }

    public String getLibraryAddress() {
        return libraryAddress;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public HashMap<String, LibraryUser> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return "Library Name: " + libraryName +
               "\nAddress: " + libraryAddress +
               "\nHours: " + activeHours() +
               "\nUsers Registered: " + users.size() +
               "\nBooks in Catalog: " + catalog.getBooks().size();
    }
}



