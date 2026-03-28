import java.util.Scanner;

// LibraryStaff class - represents an employee who works at the library
// extends LibraryUser which holds shared info like name, id, email, phone
// implements Authentication so staff can log in with their credentials
public class LibraryStaff extends LibraryUser implements Authentication {

    private String staffId;
    private String role;

    // constructor - creates a new library staff member
    public LibraryStaff(String name, String staffId, String pin, String email, String phone, String role) {
        super(staffId, pin, name, phone, email);
        this.staffId = staffId;
        this.role = role;
    }

    // tells us what a staff member is allowed to do in the system
    public String getPermissions() {
        return "Staff can add/remove books, register members, and waive fines";
    }

    // defines the role of this user in the system
    @Override
    public String defineRole() {
        return "Staff";
    }

    // checks if the entered pin matches the staff member's pin
    // throws InvalidPinException if the pin is null or empty
    @Override
    public boolean authenticate(String inputPin) {
        try {
            if (inputPin == null || inputPin.trim().isEmpty()) {
                throw new InvalidPinException("Pin cannot be null or empty.");
            }
            return this.pin.equals(inputPin);
        } catch (InvalidPinException e) {
            System.out.println("Authentication error: " + e.getMessage());
            return false;
        }
    }

    // returns true if authentication passed
    @Override
    public boolean returnIPassOrNoPass() {
        return true;
    }

    // shows the menu for staff members after they log in
    public void showMenu(LibraryStaff staff, Library library) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nHello " + staff.getName() + "! What would you like to do?");

        boolean running = true;
        while (running) {
            System.out.println("\n====== Staff Menu ======");
            System.out.println("1 - Add a book to the library");
            System.out.println("2 - Remove a book from the library");
            System.out.println("3 - View all books");
            System.out.println("4 - Waive a member's fine");
            System.out.println("5 - Search books by title");
            System.out.println("6 - Update my role");
            System.out.println("7 - Logout");
            System.out.println("========================");
            System.out.print("Enter a number: ");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                // ask for book details and add it
                System.out.print("Enter book ID: ");
                String bookId = scanner.nextLine();
                System.out.print("Enter title: ");
                String title = scanner.nextLine();
                System.out.print("Enter author: ");
                String author = scanner.nextLine();
                System.out.print("Enter ISBN: ");
                String isbn = scanner.nextLine();
                Book newBook = new Book(bookId, title, author, isbn);
                addBook(newBook, library);

            } else if (choice.equals("2")) {
                // ask for book ID and remove it
                System.out.print("Enter book ID to remove: ");
                String bookId = scanner.nextLine();
                Book bookToRemove = library.getBook(bookId);
                if (bookToRemove == null) {
                    System.out.println("Book not found.");
                } else {
                    removeBook(bookToRemove, library);
                }

            } else if (choice.equals("3")) {
                // show all books in the library
                library.showAllBooks();

            } else if (choice.equals("4")) {
                // waive a fine - ask for member ID
                System.out.print("Enter member ID to waive fine for: ");
                String memberId = scanner.nextLine();
                LibraryUser user = library.getUsers().get(memberId);
                if (user instanceof Member) {
                    waiveFine((Member) user);
                } else {
                    System.out.println("Member not found.");
                }

            } else if (choice.equals("5")) {
                // search books by title
                System.out.print("Enter title to search: ");
                String title = scanner.nextLine();
                library.searchBooksByTitle(title);

            } else if (choice.equals("6")) {
                System.out.print("Enter your new role title: ");
                String newRole = scanner.nextLine();
                staff.updateRole(newRole);

            } else if (choice.equals("7")) {
                System.out.println("Logging out. Goodbye " + staff.getName() + "!");
                running = false;

            } else {
                System.out.println("Hmm, that's not a valid option. Try again!");
            }
        }
    }

    // adds a new member to the library
    // throws MemberNotFoundException if member is null
    public void registerMember(Member member, Library library) {
        try {
            if (member == null) {
                throw new MemberNotFoundException("Member cannot be null.");
            }
            library.addUser(member);
            System.out.println(member.getName() + " has been registered.");
        } catch (MemberNotFoundException e) {
            System.out.println("Registration error: " + e.getMessage());
        } catch (DuplicateUserException e) {
            System.out.println("Registration error: " + e.getMessage());
        }
    }

    // adds a book to the library
    // throws BookNotFoundException if book is null
    public void addBook(Book book, Library library) {
        try {
            if (book == null) {
                throw new BookNotFoundException("Book cannot be null.");
            }
            library.addBook(book);
            System.out.println(book.getTitle() + " has been added.");
        } catch (BookNotFoundException e) {
            System.out.println("Add book error: " + e.getMessage());
        }
    }

    // removes a book from the library
    // throws BookNotFoundException if book is null
    public void removeBook(Book book, Library library) {
        try {
            if (book == null) {
                throw new BookNotFoundException("Book cannot be null.");
            }
            library.removeBook(book);
            System.out.println(book.getTitle() + " has been removed.");
        } catch (BookNotFoundException e) {
            System.out.println("Remove book error: " + e.getMessage());
        }
    }

    // waives all fines for a member
    // throws MemberNotFoundException if member is null
    public void waiveFine(Member member) {
        try {
            if (member == null) {
                throw new MemberNotFoundException("Member not found.");
            }
            double amount = member.getFines();
            member.setOutstandingFines(0.0);
            System.out.println("Waived $" + amount + " for " + member.getName());
        } catch (MemberNotFoundException e) {
            System.out.println("Waive fine error: " + e.getMessage());
        }
    }

    // clears any holds on a book so it can be borrowed again
    // throws BookNotFoundException if book is null
    public void clearHold(Book book) {
        try {
            if (book == null) {
                throw new BookNotFoundException("Book not found.");
            }
            book.clearReservations();
            System.out.println("Hold cleared for: " + book.getTitle());
        } catch (BookNotFoundException e) {
            System.out.println("Clear hold error: " + e.getMessage());
        }
    }

    // updates the staff member's role title
    public void updateRole(String newRole) {
        if (newRole == null || newRole.trim().isEmpty()) {
            System.out.println("Error: role cannot be empty.");
            return;
        }
        this.role = newRole;
        System.out.println("Role updated to: " + newRole);
    }

    // getters
    public String getStaffId() { return staffId; }
    public String getRole() { return role; }

    // setters
    public void setRole(String role) { this.role = role; }

    // prints out staff info
    @Override
    public String toString() {
        return "Staff ID: " + staffId + " | Name: " + name + " | Role: " + role;
    }
}