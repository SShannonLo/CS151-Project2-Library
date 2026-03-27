import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

// Loan class - represents a single borrowing transaction
// tracks which member borrowed which book and handles fines
public class Loan {

    // fine amount per day overdue
    private static final double FINE_PER_DAY = 0.25;

    private String loanId;
    private Member member;
    private Book book;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private boolean isReturned;
    private double fine;

    // constructor - creates a new loan for a member borrowing a book
    public Loan(String loanId, Member member, Book book) {
        this.loanId = loanId;
        this.member = member;
        this.book = book;
        this.borrowDate = LocalDate.now();
        this.dueDate = LocalDate.now().plusDays(14);
        this.isReturned = false;
        this.fine = 0.0;
    }

    // calculates fine based on how many days overdue the book is
    public double calculateFine() {
        if (!isOverdue()) {
            return 0.0;
        }
        long daysOverdue = ChronoUnit.DAYS.between(dueDate, LocalDate.now());
        fine = daysOverdue * FINE_PER_DAY;
        return fine;
    }

    // returns true if today is past the due date
    public boolean isOverdue() {
        if (isReturned) {
            return false;
        }
        return LocalDate.now().isAfter(dueDate);
    }

    // extends the due date by a given number of days
    public void extendCheckout(int days) {
        if (isReturned) {
            System.out.println("Error: this loan is already closed.");
            return;
        }
        if (days <= 0) {
            System.out.println("Error: days must be a positive number.");
            return;
        }
        dueDate = dueDate.plusDays(days);
        System.out.println("Due date extended to: " + dueDate);
    }

    // marks the loan as returned and finalizes any fines
    public void processReturn() {
        if (isReturned) {
            System.out.println("Error: book has already been returned.");
            return;
        }
        this.isReturned = true;
        this.fine = calculateFine();
        book.setAvailable(true);
        System.out.println("Book returned. Fine: $" + fine);
    }

    // getters
    public String getLoanId() { return loanId; }
    public Member getMember() { return member; }
    public Book getBook() { return book; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public LocalDate getDueDate() { return dueDate; }
    public boolean isReturned() { return isReturned; }
    public double getFine() { return fine; }

    // setters
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public void setFine(double fine) { this.fine = fine; }

    // prints out loan info
    @Override
    public String toString() {
        return "Loan ID: " + loanId +
                " | Member: " + member.getName() +
                " | Book: " + book.getTitle() +
                " | Due: " + dueDate +
                " | Returned: " + isReturned +
                " | Fine: $" + fine;
    }
}