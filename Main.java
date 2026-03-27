
import java.util.Scanner; // Keeping imports to the minimum
public class Main {
	private static String greeting = "            Welcome to the library system!";
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		// We could scale it to multiple libraries if we need to.
		Library libraryCityLibrary = new Library("City Library", "123 Main St", "9:00 AM", 
				"6:00 PM", "10:00 AM - 4:00 PM", 100, 100);
				
		giveGreeting(libraryCityLibrary);
		seedUsers(libraryCityLibrary);
		seedBooks(libraryCityLibrary);
		
	    while (true) {
	    	LibraryUser user = askLoginAndAuthenticate(libraryCityLibrary);
	    }
		
	}
	
	// Outputs the greeting message to the console
	public static void giveGreeting(Library libraryCityLibrary) {
		System.out.println("_____________^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^_____________");
		System.out.println(greeting);
		System.out.println("_____________^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^_____________");
		System.out.println(libraryCityLibrary.activeHours());
	}
	
	// User authentication
	public static LibraryUser askLoginAndAuthenticate(Library libraryCityLibrary) {
		
		// Keep waiting for the user to give correct input.
		while(true) {
			System.out.println("Enter your numeric ID, or X to exit, please: ");
			String idInput = scanner.nextLine();
			checkExit(idInput);
		
			System.out.println("Enter your numeric PIN, or X to exit, please: ");
			String pinInput = scanner.nextLine();
			checkExit(pinInput);
			
			try {
				// Try assigning these variables
				LibraryUser user = libraryCityLibrary.login(idInput, pinInput);
				
				// Depending of which user you have, show their menu
				if (user instanceof Member) {
				    Member member = (Member) user;
				    member.showMenu();
				} else if (user instanceof LibraryStaff) {
				    LibraryStaff staff = (LibraryStaff) user;
				    staff.showMenu(staff);
				}
				
				if(user != null) {
					return user;
 				}
				
				System.out.println("Invalid. Please try again. \n");
			} catch (NumberFormatException e) {
				
				System.out.println("Please, enter valid numbers. \n");
			}
			
		}
		
	}
	public static void checkExit(String input) {
		if(input.equalsIgnoreCase("X")) {
			System.out.println("\nGoodbye! Come again to the City Library. ");
			System.exit(0); // We completely exit out of the program here.
		}
	}
	// Adding some seed users and one librarian
	public static void seedUsers(Library libraryCityLibrary) {
        libraryCityLibrary.addUser(new Member( "Angelina", "018134168", "angelina.ryabechenkova@sjsu.edu", "4254290000", "00", libraryCityLibrary));
        libraryCityLibrary.addUser(new Member("David", "018134169", "david.doe@sjsu.edu", "4254291111", "11", libraryCityLibrary));
        libraryCityLibrary.addUser(new LibraryStaff("Librarian Bob","110110110", "22", "bob.bookson@sjsu.edu", "4254292222", "Staff"));
    }
	
	// Adding some books to our library. Note that Book id = to isbn
	public static void seedBooks(Library libraryCityLibrary) {
	    libraryCityLibrary.addBook(new Book("978", "The Great Gatsby", "F. Scott Fitzgerald", "978"));
	    libraryCityLibrary.addBook(new Book("979", "To Kill a Mockingbird", "Harper Lee", "979"));
	    libraryCityLibrary.addBook(new Book("976", "1984", "George Orwell", "976"));
	    libraryCityLibrary.addBook(new Book("975", "Pride and Prejudice", "Jane Austen", "975"));
	    libraryCityLibrary.addBook(new Book("974", "The Catcher in the Rye", "J.D. Salinger", "974"));
	}
	
	
}

