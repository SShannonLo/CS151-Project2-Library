import java.util.ArrayList; // Keeping imports to the minimum
import java.util.Scanner;
public class Member extends LibraryUser implements Authentication{ 
    
    private String role = "library member";
    private double fines;
    private ArrayList<Book> borrowedBooks; // each member has a list of books under their name
    private ArrayList<Loan> loans;
    private Library library; // Giving access to library, since I dont inherit from it
    
    public Member(String name, String memberId, String email, String phoneNumber, String pin, Library library) {
    	// Assuming LibraryUser constructor has name, id, email, and phone fields, as per the UML
    	super(memberId, pin, name, phoneNumber, email);
    	
    	this.library = library;
        //this.role = role; // preassigned
        this.fines = 0.0; // 0.0 for new users
        this.borrowedBooks = new ArrayList<>();
        this.loans = new ArrayList<>();
    }
    
    public String getPin() {
        return pin;
    }

	@Override
	public String defineRole() {
		return "Member";
	}

	public void showMenu() {
	    Scanner scanner = new Scanner(System.in);
	    String choice;
	    while (true) {
	        System.out.println("\nWelcome " + getName());
	        System.out.println("1. View profile");
	        System.out.println("2. Browse books");
	        System.out.println("3. Checkout book");
	        System.out.println("4. Pay fees");
	        System.out.println("5. Close account");
	        System.out.println("6. Return book");
	        System.out.println("X. Exit");
	        choice = scanner.nextLine();
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
	            default: System.out.println("Invalid input");
	        }
	    }
	}
	
	public boolean closeAccount() {
	    Scanner scanner = new Scanner(System.in);

	    if (!borrowedBooks.isEmpty()) {
	        System.out.println("Account cannot be closed. Please return all borrowed books first.");
	        return false;
	    }

	    if (fines > 0) {
	        System.out.println("Account cannot be closed. Outstanding fines: $" + fines);
	        return true;
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
	        return true;
	    } else {
	        System.out.println("Account closure failed.");
	        return false;
	    }
	}

	public void checkoutBook() {
	    Scanner scanner = new Scanner(System.in);
	    System.out.print("Enter ISBN of the book to check out: ");
	    String isbn = scanner.nextLine();
	    Book book = library.getBook(isbn);
	    if (book == null) {
	        System.out.println("Book not found.");
	        return;
	    }
	    if (!book.isAvailable()) {
	        System.out.println("That book is already checked out.");
	        return;
	    }
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
	    
	    System.out.println("Checkout successful!");
	    System.out.println("Book: " + book.getTitle());
	    System.out.println("Due date: " + loan.getDueDate());
	}
	
	public void returnBook() {
	    Scanner scanner = new Scanner(System.in);

	    if (borrowedBooks.isEmpty()) {
	        System.out.println("You have no borrowed books to return.");
	        return;
	    }

	    System.out.println("Your borrowed books:");
	    for (Book book : borrowedBooks) {
	        System.out.println(book);
	    }

	    System.out.print("Enter ISBN of the book to return: ");
	    String isbn = scanner.nextLine();

	    Book bookToReturn = null;
	    for (Book book : borrowedBooks) {
	        if (book.getIsbn().equals(isbn)) {
	            bookToReturn = book;
	            break;
	        }
	    }

	    if (bookToReturn == null) {
	        System.out.println("This book is not borrowed under your account.");
	        return;
	    }

	    Loan matchingLoan = null;
	    for (Loan loan : loans) {
	        if (loan.getBook().getIsbn().equals(isbn) && !loan.isReturned()) {
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
	//since we can't access them directly, we have to use polymprphism
	public void browseBooks() {
		System.out.println("\n----- Available Books -----");
	    library.showAllBooks();
	    System.out.println("---------------------------");
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
	    sb.append("--------------------------");
	    return sb.toString();
	}
	public double getOutstandingFines() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void setOutstandingFines(double d) {
		// TODO Auto-generated method stub
		
	}
	
	

	@Override
	public boolean authenticate(String inputPin) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean returnIPassOrNoPass() {
		// TODO Auto-generated method stub
		return false;
	}
}








