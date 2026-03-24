// LibraryStaff class - represents an employee who works at the library
// extends LibraryUser which holds shared info like name, email, phone
public class LibraryStaff extends LibraryUser {

    private String staffId;
    private String role;

    // constructor - creates a new library staff member
    public LibraryStaff(String name, String staffId, String email, String phone, String role) {
        super(name, staffId, email, phone);
        this.staffId = staffId;
        this.role = role;
    }

    // tells us what a staff member is allowed to do in the system
    @Override
    public String getPermissions() {
        return "Staff can add/remove books, register members, and waive fines";
    }

    // adds a new member to the library
    public void registerMember(Member member, Library library) {
        if (member == null) {
            System.out.println("Error: member cannot be null.");
            return;
        }
        library.addMember(member);
        System.out.println(member.getName() + " has been registered.");
    }

    // adds a book to a branch
    public void addBook(Book book, Branch branch) {
        if (book == null) {
            System.out.println("Error: book cannot be null.");
            return;
        }
        branch.addBook(book);
        System.out.println(book.getTitle() + " has been added.");
    }

    // removes a book from a branch
    public void removeBook(Book book, Branch branch) {
        if (book == null) {
            System.out.println("Error: book cannot be null.");
            return;
        }
        branch.removeBook(book);
        System.out.println(book.getTitle() + " has been removed.");
    }

    // waives all fines for a member
    public void waiveFine(Member member) {
        if (member == null) {
            System.out.println("Error: member not found.");
            return;
        }
        double amount = member.getOutstandingFines();
        member.setOutstandingFines(0.0);
        System.out.println("Waived $" + amount + " for " + member.getName());
    }

    // clears all reservations on a book
    public void clearReservation(Book book) {
        if (book == null) {
            System.out.println("Error: book not found.");
            return;
        }
        book.clearReservations();
        System.out.println("Reservations cleared for: " + book.getTitle());
    }

    // updates the staff member's role title
    public void updateRole(String newRole) {
        if (newRole == null || newRole.trim().isEmpty()) {
            System.out.println("Error: role cannot be empty.");
            return;
        }
        this.role = newRole;
        System.out.println("Role updated to: " + newRole);
    }

    // getters
    public String getStaffId() { return staffId; }
    public String getRole() { return role; }

    // setters
    public void setRole(String role) { this.role = role; }

    // prints out staff info
    @Override
    public String toString() {
        return "Staff ID: " + staffId + " | Name: " + name + " | Role: " + role;
    }
}