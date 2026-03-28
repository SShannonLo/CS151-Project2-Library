import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MemberTest {

    private Member makeMember() {
        Library library = new Library("Test Library", "Test Address", "9", "5", "10-4", 100, 100);
        return new Member("TestUser", "123", "test@test.com", "1234567890", "00", library);
    }

    @Test
    public void testMemberCreation() {
        Member member = makeMember();
        assertNotNull(member);
        assertEquals("TestUser", member.getName());
    }

    @Test
    public void testLibraryCardCreated() {
        Member member = makeMember();
        assertNotNull(member.getLibraryCard());
    }

    @Test
    public void testAddReservation() {
        Member member = makeMember();
        Reservation r = new Reservation("R1", member, "Room A", "2026-03-30", "10:00");
        member.addRoomReservation(r);
        assertEquals(1, member.getRoomReservations().size());
    }

    @Test
    public void testReservationData() {
        Member member = makeMember();
        Reservation r = new Reservation("R1", member, "Room A", "2026-03-30", "10:00");
        member.addRoomReservation(r);
        assertEquals("Room A", member.getRoomReservations().get(0).getRoomName());
    }
}