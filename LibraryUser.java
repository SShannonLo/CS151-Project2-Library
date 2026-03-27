
public abstract class LibraryUser {
	protected int id;
	protected int pin;
	protected String name;
	
	// Constructor for the LibraryUser class
	public LibraryUser(int id, int pin, String name ) {
		this.id = id;
		this.pin = pin;
		this.name = name;
		
	}
	
	public boolean authenticate(int inputPin) {
		return this.pin == inputPin; // We are comparing primitives
	}
	
	public String getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}
	
	// Abstract method for an abstract class
	public abstract String defineRole();
}
