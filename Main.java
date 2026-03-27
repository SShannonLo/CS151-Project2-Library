
import java.util.Scanner; // Keeping imports to the minimum
public class Main {
	private static String greeting = "            Welcome to the library system!";
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		giveGreeting(libraryCityLibrary);
		seedUsers(libraryCityLibrary);
		
		LibraryUser loggedInUser = askLoginAndAuthenticate(libraryCityLibrary);
		
		// We could scale it to multiple libraries if we need to.
		Library libraryCityLibrary = new Library("City Library", "123 Main St", "9:00 AM", 
				"6:00 PM", "10:00 AM - 4:00 PM", 100, 100);
		
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
		System.out.println("Enter your numeric ID, please: ");
		int id = scanner.nextInt();
		
		System.out.println("Enter your numeric PIN, please: ");
		int pin = scanner.nextInt();
		
		return libraryCityLibrary.login(id, pin);
	}
	// Adding some seed users and one librarian
	public static void seedUsers(Library libraryCityLibrary) {
        library.addUser(new Member( "Angelina", "018134168", "angelina.ryabechenkova@sjsu.edu", "4254290000"));
        library.addUser(new Member("David", "018134169", "david.doe@sjsu.edu", "4254291111"));
        library.addUser(new Staff("Librarian Bob","110110110", "bob.bookson@sjsu.edu", "4254292222"));
    }
	
}
