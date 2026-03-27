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
	            //case "4": payFees(); break;
	            //case "5": closeAccount(); return;
	            //case "6": returnBook(); break;
	            default: System.out.println("Invalid input");
	        }
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
	    book.setAvailable(false);
	    borrowedBooks.add(book);
	    loans.add(loan);
	    System.out.println("Checkout successful!");
	    System.out.println("Book: " + book.getTitle());
	    System.out.println("Due date: " + loan.getDueDate());
	}
	// This method is needed to access all books in the library, 
	//since we can't access them directly, we have to use polymprphism
	public void browseBooks() {
		System.out.println("\n----- Available Books -----");
	    library.showAllBooks();
	    System.out.println("---------------------------");
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








