import java.util.ArrayList; // Keeping imports to the minimum

public class Member extends LibraryUser{
    
    private String role = "library member";
    private double fines;
    private ArrayList<Book> borrowedBooks; // each member has a list of books under their name
    
    private String name;
    private String phoneNumber;

    
    public Member(String name, String memberId, String email, String phoneNumber) {
    	// Assuming LibraryUser constructor has name, id, email, and phone fields, as per the UML
    	super(name, memberId, email, phoneNumber);
    	
        this.role = role; // preassigned
        this.fines = 0.0; // 0.0 for new users
        this.borrowedBooks = new ArrayList<>();
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public void addFine(double amount) {
        fineAmount += amount;
    }
}
