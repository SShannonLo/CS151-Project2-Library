
public interface Borrowable{
    boolean checkOut(String memberId);
    boolean returnBook();
    void markOverDue();
    //boolean reserve(String memberId);
	boolean reserveBook(String memberId);
}
