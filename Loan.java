import java.time.LocalDate;

public class Loan {

    private String loanId;
    private String memberName;
    private String bookTitle;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private boolean isReturned;
    private double fine;

    public Loan(String loanId, String memberName, String bookTitle, LocalDate checkoutDate, LocalDate dueDate) {
        this.loanId = loanId;
        this.memberName = memberName;
        this.bookTitle = bookTitle;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.isReturned = false;
        this.fine = 0.0;
    }

    // returns true if the book is past its due date
    public boolean isOverdue() {
        return !isReturned && LocalDate.now().isAfter(dueDate);
    }

    // calculates the fine based on how many days overdue at 25 cents per day
    public double calculateFine() {
        if (!isOverdue()) {
            return 0.0;
        }
        long daysOverdue = java.time.temporal.ChronoUnit.DAYS.between(dueDate, LocalDate.now());
        fine = daysOverdue * 0.25;
        return fine;
    }

    // extends the due date by a number of days
    public void extendCheckout(int days) {
        dueDate = dueDate.plusDays(days);
        System.out.println("Due date extended to: " + dueDate);
    }

    // marks the book as returned and calculates any final fines
    public void processReturn() {
        isReturned = true;
        fine = calculateFine();
        System.out.println("Book returned. Total fine: $" + fine);
    }

    // prints a summary of the loan
    public void getSummary() {
        System.out.println("Loan ID: " + loanId);
        System.out.println("Book: " + bookTitle);
        System.out.println("Member: " + memberName);
        System.out.println("Due Date: " + dueDate);
        System.out.println("Returned: " + isReturned);
        System.out.println("Fine: $" + fine);
    }

    public String getLoanId() { return loanId; }
    public String getMemberName() { return memberName; }
    public String getBookTitle() { return bookTitle; }
    public LocalDate getCheckoutDate() { return checkoutDate; }
    public LocalDate getDueDate() { return dueDate; }
    public boolean getIsReturned() { return isReturned; }
    public double getFine() { return fine; }

    public void setIsReturned(boolean isReturned) { this.isReturned = isReturned; }
    public void setFine(double fine) { this.fine = fine; }

    @Override
    public String toString() {
        return "Loan ID: " + loanId + " | Book: " + bookTitle + " | Member: " + memberName;
    }
}