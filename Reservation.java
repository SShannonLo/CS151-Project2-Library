public class Reservation {
    private String reservationId;
    private Book book;
    private Member member;
    private boolean active;

    public Reservation(String reservationId, Book book, Member member) {
        this.reservationId = reservationId;
        this.book = book;
        this.member = member;
        this.active = true;
    }

    public void createReservation(Book book, Member member) {
        this.book = book;
        this.member = member;
        this.active = true;
        System.out.println("Reservation created.");
    }

    public void cancelReservation() {
        this.active = false;
        System.out.println("Reservation cancelled.");
    }

    public boolean isActive() {
        return active;
    }

    public String getReservationInfo() {
        return "Reservation ID: " + reservationId +
               ", Book: " + book.getTitle() +
               ", Member: " + member.getName();
    }
}