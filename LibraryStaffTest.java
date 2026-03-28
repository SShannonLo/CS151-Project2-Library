import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// This file tests the LibraryStaff class to make sure everything works correctly
// We test things like creating a staff member, logging in, and updating their role
public class LibraryStaffTest {

    // this helper method just creates a test staff member so we dont have to repeat ourselves
    private LibraryStaff makeStaff() {
        return new LibraryStaff("Bob", "110110110", "22", "bob@sjsu.edu", "4254292222", "Staff");
    }

    // make sure we can actually create a staff member and their info is saved correctly
    @Test
    public void testStaffCreation() {
        LibraryStaff staff = makeStaff();
        assertNotNull(staff); // staff should not be null
        assertEquals("Bob", staff.getName()); // name should match
        assertEquals("110110110", staff.getStaffId()); // id should match
        assertEquals("Staff", staff.getRole()); // role should match
    }

    // test that the correct pin lets the staff member log in
    @Test
    public void testAuthenticateCorrectPin() {
        LibraryStaff staff = makeStaff();
        // pin "22" is the correct one so this should return true
        assertTrue(staff.authenticate("22"));
    }

    // test that a wrong pin does NOT let the staff member log in
    @Test
    public void testAuthenticateWrongPin() {
        LibraryStaff staff = makeStaff();
        // pin "99" is wrong so this should return false
        assertFalse(staff.authenticate("99"));
    }

    // make sure defineRole returns the right role type
    @Test
    public void testDefineRole() {
        LibraryStaff staff = makeStaff();
        // staff members should always return "Staff" as their role type
        assertEquals("Staff", staff.defineRole());
    }

    // test that we can update a staff member's role title
    @Test
    public void testUpdateRole() {
        LibraryStaff staff = makeStaff();
        staff.updateRole("Manager"); // change the role
        // now the role should be Manager instead of Staff
        assertEquals("Manager", staff.getRole());
    }

    // test that authentication check returns true for valid staff
    @Test
    public void testReturnIPassOrNoPass() {
        LibraryStaff staff = makeStaff();
        // this should return true since the staff member exists
        assertTrue(staff.returnIPassOrNoPass());
    }

    // make sure updating role to empty string doesnt work
    @Test
    public void testUpdateRoleEmpty() {
        LibraryStaff staff = makeStaff();
        staff.updateRole(""); // try to set an empty role
        // role should still be Staff since empty string is not valid
        assertEquals("Staff", staff.getRole());
    }
}

