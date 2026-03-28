
import java.util.Scanner; // Keeping imports to the minimum
public class Main {
	private static String greeting = "              Welcome to the library system!";
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		// We could scale it to multiple libraries if we need to.
		Library libraryCityLibrary = new Library("City Library", "123 Main St", "9:00 AM", 
				"6:00 PM", "10:00 AM - 4:00 PM", 100, 100);
		
		// Seed users and books first, before giving the greeting.
		seedUsers(libraryCityLibrary);
		seedBooks(libraryCityLibrary);
		giveGreeting(libraryCityLibrary);
		
		// Spin loop that keeps printing the menu for Library Users to decide what they wan to do.
	    while (true) {
	    	LibraryUser user = askLoginAndAuthenticate(libraryCityLibrary);
	    }
		
	}
	
	// Outputs the greeting message to the console
	public static void giveGreeting(Library libraryCityLibrary) {
		System.out.println("_____________^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^_____________");
		System.out.println(greeting);
		System.out.println("_____________^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^_____________");
		System.out.println(libraryCityLibrary.toString());
		System.out.println("_________________________________________________________");
	}
	
	// User authentication
	public static LibraryUser askLoginAndAuthenticate(Library libraryCityLibrary) {
		
		// Keep waiting for the user to give correct input.
		while(true) {
			System.out.println("Enter your numeric ID, or EXIT to exit, please: ");
			String idInput = scanner.nextLine();
			checkExit(idInput);
		
			System.out.println("Enter your numeric PIN, or EXIT to exit, please: ");
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
					staff.showMenu(staff, libraryCityLibrary);
				}
				
				if(user != null) {
					return user;
 				}
				
				// If the user you searched for was nether Member nor Staff, nor null.
				System.out.println("Invalid. Please try again. \n");
			} catch (NumberFormatException e) {
				
				System.out.println("Please, enter valid numbers. \n");
			}
			
		}
		
	}
	// Checks if the user wants to exit the program at any point
	// Supports both "X" and "EXIT" for graceful shutdown (extra credit)
	public static void checkExit(String input) {
	    if(input.equalsIgnoreCase("X") || input.equalsIgnoreCase("EXIT")) {
	        System.out.println("\nGoodbye! Come again to the City Library. ");
	        System.exit(0); // We completely exit out of the program here.
	    }
	}
	
	// Adding some seed users and one librarian
	public static void seedUsers(Library libraryCityLibrary) {
	    try {
	        libraryCityLibrary.addUser(new Member(
	            "Angelina", "018134168",
	            "angelina.ryabechenkova@sjsu.edu",
	            "4254290000", "00",
	            libraryCityLibrary
	        ));

	        libraryCityLibrary.addUser(new Member(
	            "David", "018134169",
	            "david.doe@sjsu.edu",
	            "4254291111", "11",
	            libraryCityLibrary
	        ));

	        libraryCityLibrary.addUser(new LibraryStaff(
	            "Librarian Bob",
	            "110110110",
	            "22",
	            "bob.bookson@sjsu.edu",
	            "4254292222",
	            "Staff"
	        ));

	    } catch (DuplicateUserException e) {
	        System.out.println("Error seeding users: " + e.getMessage());
	    }
	}
	
	// Adding some books to our library.
	public static void seedBooks(Library libraryCityLibrary) {
	    libraryCityLibrary.addBook(new Book("B1", "The Great Gatsby", "F. Scott Fitzgerald", "978"));
	    libraryCityLibrary.addBook(new Book("B2", "To Kill a Mockingbird", "Harper Lee", "979"));
	    libraryCityLibrary.addBook(new Book("B3", "1984", "George Orwell", "976"));
	    libraryCityLibrary.addBook(new Book("B4", "Pride and Prejudice", "Jane Austen", "975"));
	    libraryCityLibrary.addBook(new Book("B5", "The Catcher in the Rye", "J.D. Salinger", "974"));
	}
	
	
}

