import java.util.ArrayList;

public class Book implements Borrowable{
    private static int bookCount = 0;
    public static final int MAX_COUNT = 100;

    private String bookId;
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;
    private boolean isOverdue;
    private String checkOutById;
    private ArrayList<String> reservationQueue;


    public Book(String bookId, String title, String author, String isbn){
        if(bookCount >= MAX_COUNT){
            throw new IllegalArgumentException("Maximum book limit reached");

        }
        setBookId(bookId);
        setTitle(title);
        setAuthor(author);
        setIsbn(isbn);

        this.isAvailable = true;
        this.isOverdue = false;
        this.checkOutById = null;
        this.reservationQueue = new ArrayList<>();

        bookCount++;

    }
    @Override
    public boolean checkOut(String memberId){
        if(memberId == null || memberId.trim().isEmpty()){
            System.out.println("Failed, member ID is invalid");
            return false;
        }
        if(!isAvailable){
            System.out.println("Failed, book is already checked out ");
            return false;
        }
        if(!reservationQueue.isEmpty() && !reservationQueue.get(0).equals(memberId)){
            System.out.println("Failed, book is reserved by another member");
            return false;
        }
        
        this.isAvailable = false;
        this.checkOutById = memberId;
        this.isOverdue = false;

        if(!reservationQueue.isEmpty() && reservationQueue.get(0).equals(memberId)){
            reservationQueue.remove(0);
        }

        System.out.println(memberId + ": Book checked out successfully");
        return true;

    }

    @Override
    public boolean returnBook(){
        if(isAvailable){
            System.out.println("Return failed: book is already in the catalog.");
            return false;
        }

        this.isAvailable = true;
        this.checkOutById = null;
        this.isOverdue = false;

        System.out.println("Book returned successfully");
        return true;

    }

    @Override
    public void markOverDue(){
        if(isAvailable){
            System.out.println("Book is not checked out, cannot mark as overdue.");
            return;
        }
        this.isOverdue = true;
        System.out.println("Book marked as overdue.");
    }

    @Override
    public boolean reserveBook(String memberId){
        if(memberId == null || memberId.trim().isEmpty()){
            System.out.println("Failed, member ID cannot be null or empty");
            return false;
        }
        if(reservationQueue.contains(memberId)){
            System.out.println("Failed, member has already reserved the book");
            return false;
        }
        reservationQueue.add(memberId);
        System.out.println(memberId + ": Book reserved successfully");
        return true;
    }

    public boolean cancelReservation(String memberId){
        if (reservationQueue.remove(memberId)){
            System.out.println(memberId + ": Reservation cancelled successfully");
            return true;
        }
        System.out.println("Failed, member not found in reservation queue");
        return false;
    }

    public String getNextReservedMember(){
        if(reservationQueue.isEmpty()){
            return "No reservations";
        }
        return reservationQueue.get(0);
    }

    public boolean isAvailableForCheckout(){
        return isAvailable;
    }

     public void displayBookStatus() {
        System.out.println("Book ID: " + bookId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + isbn);
        System.out.println("Available: " + isAvailable);
        System.out.println("Overdue: " + isOverdue);
        System.out.println("Checked out by: " + checkOutById);
        System.out.println("Reservation queue: " + reservationQueue);
    }

    public String getBookId(){
        return bookId;
    }

    public void setBookId(String bookId){
        if(bookId == null || bookId.trim().isEmpty()){
            throw new IllegalArgumentException("Book ID cannot be null or empty");
        }
        this.bookId = bookId;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        if (title == null || title.trim().isEmpty()){
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        this.title = title;
    }

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author){
        if(author == null || author.trim().isEmpty()){
            throw new IllegalArgumentException("Author cannot be null or empty");
        }
        this.author = author;
    }

    public String getIsbn(){
        return isbn;
    }

    public void setIsbn(String isbn){
        if(isbn == null || isbn.trim().isEmpty()){
            throw new IllegalArgumentException("ISBN cannot be null or empty");
        }
        this.isbn = isbn;
    }

    public boolean isAvailable(){
        return isAvailable;
    }

    public boolean isOverdue(){
        return isOverdue;
    }
    public String getCheckOutById(){
        return checkOutById;
    }

    public ArrayList<String> getReservationQueue(){
        return reservationQueue;
    }

    @Override
    public String toString() {
        return "Book: " +
                "bookId='" + bookId + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", available=" + isAvailable +
                ", overdue=" + isOverdue +
                ", checkedOutByMemberId='" + checkOutById + '\'' +
                ", reservationQueue=" + reservationQueue +
                '}';
    }



    // FIXME this was autogenerated from LibraryStaff method bc it requires it
	public void clearReservations() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean reserve(String memberId) {
		// TODO Auto-generated method stub
		return false;
	}
	public void setAvailable(boolean b) {
		this.isAvailable = b;
		
	}
}

