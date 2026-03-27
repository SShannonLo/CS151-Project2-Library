// Authentication interface - defines login behavior for library users
// implemented by LibraryStaff and LibraryMember
public interface Authentication {

    // checks if the entered pin matches the user's pin
    boolean authenticate(String inputPin);

    // returns true if the user passed authentication
    boolean returnIPassOrNoPass();
}