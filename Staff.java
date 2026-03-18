public class Staff {

    private String staffId;
    private String name;
    private String role;
    private String email;
    private String phone;

    public Staff(String staffId, String name, String role, String email, String phone) {
        this.staffId = staffId;
        this.name = name;
        this.role = role;
        this.email = email;
        this.phone = phone;
    }

    // adds a book to the library collection
    public void addBook(Book book) {
        System.out.println(book.getTitle() + " has been added to the library.");
    }

    // removes a book from the library collection
    public void removeBook(Book book) {
        System.out.println(book.getTitle() + " has been removed from the library.");
    }

    // searches for a book by title
    public void searchBook(String title) {
        System.out.println("Searching for: " + title);
    }

    // registers a new member into the system
    public void registerMember(Member member) {
        System.out.println(member.getName() + " has been registered as a member.");
    }

    // waives any outstanding fines a member has
    public void waiveFine(Member member) {
        System.out.println("Fine waived for: " + member.getName());
    }

    public String getStaffId() { return staffId; }
    public String getName() { return name; }
    public String getRole() { return role; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }

    public void setName(String name) { this.name = name; }
    public void setRole(String role) { this.role = role; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }

    @Override
    public String toString() {
        return "Staff ID: " + staffId + " | Name: " + name + " | Role: " + role;
    }
}