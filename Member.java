import java.util.ArrayList; // Keeping imports to the minimum

public class Member extends LibraryUser{
    
    private String role = "library member";
    private double fines;
    private ArrayList<Book> borrowedBooks; // each member has a list of books under their name
    
    private String name;
    private String phoneNumber;
    private String email;
    private int memberId;

    
    public Member(String name, String memberId, String email, String phoneNumber, String pin) {
    	// Assuming LibraryUser constructor has name, id, email, and phone fields, as per the UML
    	super(memberId, pin, name, phoneNumber, email);
    	
        this.role = role; // preassigned
        this.fines = 0.0; // 0.0 for new users
        this.borrowedBooks = new ArrayList<>();
    }

    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    //public double getFineAmount() {
    //    return fineAmount;
    //}

    //public void addFine(double amount) {
    //    fineAmount += amount;
    //}

	@Override
	public String defineRole() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void showMenu() {
	    System.out.println("Member menu here...");
	}
}
