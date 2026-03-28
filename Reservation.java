public class Reservation {
    private String reservationId;
    private Member member;
    private String roomName;
    private String reservationDate;
    private String timeSlot;
    private boolean active;

    public Reservation(String reservationId, Member member, String roomName, String reservationDate, String timeSlot) {
        this.reservationId = reservationId;
        this.member = member;
        this.roomName = roomName;
        this.reservationDate = reservationDate;
        this.timeSlot = timeSlot;
        this.active = true;
    }

    public String getReservationId() {
        return reservationId;
    }

    public Member getMember() {
        return member;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public boolean isActive() {
        return active;
    }

    public void cancelReservation() {
        active = false;
    }

    public void confirmReservation() {
        active = true;
    }

    @Override
    public String toString() {
        return "Reservation ID: " + reservationId +
                "\nMember: " + member.getName() +
                "\nRoom: " + roomName +
                "\nDate: " + reservationDate +
                "\nTime: " + timeSlot +
                "\nStatus: " + (active ? "Active" : "Cancelled");
    }
}