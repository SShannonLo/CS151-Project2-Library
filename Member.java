import java.util.ArrayList; // Keeping imports to the minimum
import java.util.Scanner;

public class Member extends LibraryUser implements Authentication{ 
    
    private String role = "library member";
    private double fines;
    private ArrayList<Book> borrowedBooks; // Each member has a list of books under their name.
    private ArrayList<Loan> loans;
    private Library library; // Giving access to library, since I dont inherit from it
    private LibraryCard libraryCard;
	private ArrayList<Reservation> roomReservations = new ArrayList<>();
    
    // Constructor for the Member class
    public Member(String name, String memberId, String email, String phoneNumber, String pin, Library library) {
        
    	// Pass param-s to the LibraryMember constructor.
    	super(memberId, pin, name, phoneNumber, email);

        this.library = library;
        this.role = "library member";
        this.fines = 0.0;
        this.borrowedBooks = new ArrayList<>(); // What the user currently has
        this.loans = new ArrayList<>(); // Records of the past transactions

        // Shows integration with the library card.
        this.libraryCard = new LibraryCard("CARD-" + memberId, this, "2027-12-31");
    }
    
    // Helper.
    public LibraryCard getLibraryCard() {
        return libraryCard;
    }
    
    // Helper.
    public String getPin() {
        return pin;
    }

    // Implementation of the abstract method.
	@Override
	public String defineRole() {
		return "Member";
	}

	// Shows the Member menu.
	public void addRoomReservation(Reservation reservation) {
		roomReservations.add(reservation);
	}

	public ArrayList<Reservation> getRoomReservations() {
		return roomReservations;
	}

	public void showMenu() {
	    Scanner scanner = new Scanner(System.in);
	    String choice;
	    
	    // Spin loop
	    while (true) {
	        System.out.println("\nWelcome " + getName());
	        System.out.println("1. View profile");
	        System.out.println("2. Browse books");
	        System.out.println("3. Checkout book");
	        System.out.println("4. Pay fees");
	        System.out.println("5. Close account");
	        System.out.println("6. Return book");
			System.out.println("7. View room reservations");
			System.out.println("8. Reserve study room");
	        System.out.println("X. Exit");
	        choice = scanner.nextLine();
	        Main.checkExit(choice);
	        if (choice.equalsIgnoreCase("X")) {
	            break;
	        }
	        switch (choice) {
	            case "1": System.out.println(this); break;
	            case "2": browseBooks(); break;
	            case "3": checkoutBook(); break;
	            case "4": payFees(); break;
	            case "5": if (closeAccount()) {
	                			return;
	            			}
	           			  break;
	            case "6": returnBook(); break;
				case "7": viewRoomReservations(); break;
				case "8": reserveRoom(); break;
	            default: System.out.println("Invalid input");
	        }
	    }
	}
	
	// Performs checks if the account is eligible for closing.
	public boolean closeAccount() {
	    Scanner scanner = new Scanner(System.in);

	    if (!borrowedBooks.isEmpty()) {
	        System.out.println("Account cannot be closed. Please return all borrowed books first.");
	        return false;
	    }

	    if (fines > 0) {
	        System.out.println("Account cannot be closed. Outstanding fines: $" + fines);
	        return false;
	    }

	    System.out.print("Are you sure you want to close your account? (Y/N): ");
	    String choice = scanner.nextLine();

	    if (!choice.equalsIgnoreCase("Y")) {
	        System.out.println("Account closure cancelled.");
	        return false;
	    }

	    boolean removed = library.removeUser(getId());

	    if (removed) {
	        System.out.println("Your account has been closed successfully.");
	        libraryCard.deactivateCard();
	        return true;
	    } else {
	        System.out.println("Account closure failed.");
	        return false;
	    }
	}

	// Performs checks if the needed book can be checked out.
	public void checkoutBook() {
	    Scanner scanner = new Scanner(System.in);

	    if (!libraryCard.checkValidity()) {
	        System.out.println("Your library card is not valid.");
	        return;
	    }

	    System.out.print("Enter Book ID of the book to check out: ");
	    String bookId = scanner.nextLine();

	    Book book = library.getBook(bookId);
	    if (book == null) {
	        System.out.println("Book not found.");
	        return;
	    }

	    if (!book.isAvailable()) {
	        System.out.println("That book is already checked out.");
	        return;
	    }

	    // Records the checkout time.
	    String loanId = "L" + System.currentTimeMillis();
	    Loan loan = new Loan(loanId, this, book);

	    if (book.checkOut(this.getId())) {
	        borrowedBooks.add(book);
	        loans.add(loan);
	        System.out.println("Checkout successful!");
	        System.out.println("Book: " + book.getTitle());
	        System.out.println("Due date: " + loan.getDueDate());
	    } else {
	        System.out.println("Checkout failed.");
	    }
	}
	
	// Performs checks for book return.
	public void returnBook() {
	    Scanner scanner = new Scanner(System.in);

	    if (borrowedBooks.isEmpty()) {
	        System.out.println("You have no borrowed books to return.");
	        return;
	    }

	    System.out.println("Your borrowed books:");
	    for (Book borrowedBook : borrowedBooks) {
	        System.out.println(borrowedBook);
	    }

	    System.out.print("Enter Book ID of the book to return: ");
	    String bookId = scanner.nextLine();

	    Book bookToReturn = null;
	    for (Book borrowedBook : borrowedBooks) {
	        if (borrowedBook.getBookId().equals(bookId)) {
	            bookToReturn = borrowedBook;
	            break;
	        }
	    }

	    if (bookToReturn == null) {
	        System.out.println("This book is not borrowed under your account.");
	        return;
	    }

	    Loan matchingLoan = null;
	    for (Loan loan : loans) {
	        if (loan.getBook().getBookId().equals(bookId) && !loan.isReturned()) {
	            matchingLoan = loan;
	            break;
	        }
	    }

	    if (matchingLoan == null) {
	        System.out.println("No active loan found for this book.");
	        return;
	    }

	    double fineAmount = matchingLoan.calculateFine();

	    boolean returned = bookToReturn.returnBook();
	    if (!returned) {
	        System.out.println("Return failed.");
	        return;
	    }

	    matchingLoan.processReturn();
	    borrowedBooks.remove(bookToReturn);

	    if (fineAmount > 0) {
	        fines += fineAmount;
	        System.out.println("Fine added to your account: $" + fineAmount);
	    }

	    System.out.println("Return completed successfully.");
	}
	
	// This method is needed to access all books in the library, 
	// since we can't access them directly, we have to use polymprphism
	public void browseBooks() {
		System.out.println("\n----- Available Books -----");
	    library.showAllBooks();
	    System.out.println("---------------------------");
	}

	public void viewRoomReservations() {
    	System.out.println("\n----- Room Reservations -----");

    	if (roomReservations.isEmpty()) {
       		System.out.println("No reservations found.");
   	 	} else {
      		for (Reservation r : roomReservations) {
            	System.out.println(r);
            	System.out.println("---------------------");
        	}
    	}
	}

	public void reserveRoom() {
    	Scanner scanner = new Scanner(System.in);

    	System.out.println("Available Rooms:");
    	System.out.println("1. Room A");
    	System.out.println("2. Room B");
    	System.out.println("3. Room C");
    	System.out.println("4. Room D");
    	System.out.println("5. Room E");

    	System.out.print("Choose a room (1-5): ");
    	String choice = scanner.nextLine();

    	String room;

    	switch (choice) {
    	    case "1":
    	        room = "Room A";
    	        break;
    	    case "2":
    	        room = "Room B";
    	        break;
    	    case "3":
    	        room = "Room C";
    	        break;
    	    case "4":
    	        room = "Room D";
    	        break;
    	    case "5":
    	        room = "Room E";
    	        break;
    	    default:
    	        System.out.println("Invalid choice.");
    	        return;
    	}

    	System.out.print("Enter date (YYYY-MM-DD): ");
    	String date = scanner.nextLine();

    	System.out.print("Enter time (HH:MM): ");
    	String time = scanner.nextLine();

    	String reservationId = "R" + System.currentTimeMillis();

    	Reservation reservation = new Reservation(reservationId, this, room, date, time);
    	addRoomReservation(reservation);

    	System.out.println("Room " + room + " reserved successfully!");
	}
	
	public void payFees() {
	    Scanner scanner = new Scanner(System.in);

	    if (fines <= 0) {
	        System.out.println("You have no outstanding fees.");
	        return;
	    }

	    System.out.println("Outstanding fees: $" + fines);
	    System.out.print("Enter payment amount: $");

	    double payment;
	    try {
	        payment = Double.parseDouble(scanner.nextLine());
	    } catch (NumberFormatException e) {
	        System.out.println("Invalid payment amount.");
	        return;
	    }

	    if (payment <= 0) {
	        System.out.println("Payment must be greater than 0.");
	        return;
	    }

	    if (payment >= fines) {
	        double change = payment - fines;
	        fines = 0.0;
	        System.out.println("Fees paid in full.");
	        if (change > 0) {
	            System.out.println("Change: $" + change);
	        }
	    } else {
	        fines -= payment;
	        System.out.println("Partial payment accepted.");
	        System.out.println("Remaining balance: $" + fines);
	    }
	}
	
	public void addFine(double amount) {
	    if (amount > 0) {
	        fines += amount;
	    }
	}
	
	public double getFines() {
	    return fines;
	}
	
	
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("----- Member Profile -----\n");
	    sb.append(super.toString()).append("\n"); // prints LibraryUser info
	    sb.append("Role: ").append(role).append("\n");
	    sb.append("Fines: $").append(fines).append("\n");
	    sb.append("Borrowed Books:\n");
	    if (borrowedBooks.isEmpty()) {
	        sb.append("  None\n");
	    } else {
	        for (Book book : borrowedBooks) {
	            sb.append("  - ").append(book.getTitle()).append("\n");
	        }
	    }
	    sb.append("Room Reservations:\n");
	    if (roomReservations.isEmpty()) {
	        sb.append("  None\n");
	    } else {
	        for (Reservation r : roomReservations) {
	            sb.append("  - ").append(r.getRoomName())
				  .append(" on ").append(r.getReservationDate())
				  .append(" at ").append(r.getTimeSlot())
				  .append("\n");
	        }
	    }
	    sb.append("Library Card ID: ").append(libraryCard.getCardId()).append("\n");
	    sb.append("Card Expiration: ").append(libraryCard.getExpirationDate()).append("\n");
	    sb.append("--------------------------");
	    return sb.toString();
	}
	
	public double getOutstandingFines() {
		return fines;
	}
	public void setOutstandingFines(double d) {
		if (d < 0) {
	        System.out.println("Fines cannot be negative.");
	        return;
	    }
	    fines = d;
		
	}
	
	@Override
	public boolean authenticate(String inputPin) {
		return this.pin.equals(inputPin);
	}

	@Override
	public boolean returnIPassOrNoPass() {
		return libraryCard != null && libraryCard.checkValidity();
	}
}








