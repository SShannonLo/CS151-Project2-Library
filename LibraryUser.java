
public abstract class LibraryUser {
	protected String id;
	protected String pin;
	protected String name;
    protected String phoneNumber;
    protected String email;
	
	// Constructor for the LibraryUser class
	public LibraryUser(String id, String pin, String name, String phoneNumber, String email) {
		this.id = id;
		this.pin = pin;
		this.name = name;
		
	}
	
	public boolean authenticate(int inputPin) {
		return (this.pin).equals(inputPin); // We are comparing primitives
	}
	
	public String getName() {
		return name;
	}
	
	public String getId() {
		return id;
	}
	
	// Abstract method for an abstract class
	public abstract String defineRole();
}
