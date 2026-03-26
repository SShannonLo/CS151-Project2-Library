
import java.util.Scanner; // Keeping imports to the minimum
public class Main {
	private static String greeting = "            Welcome to the library system!";
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		giveGreeting();
	}
	
	// Outputs the greeting message to the console
	public static void giveGreeting() {
		System.out.println("_____________^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^_____________");
		System.out.println(greeting);
		System.out.println("_____________^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^_____________");
	}
	
}
