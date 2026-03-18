public class Library {

    public static class Member {
        private String name;
        private boolean isActive;
        private String membershipType;

        // Member Constructor for the class
        public Member(String name, boolean isActive, String membershipType) {
            this.name = name;
            this.isActive = isActive;
            this.membershipType = membershipType;
        }

        // Getters 
        public String getName() {
            return name;
        }
        
        public boolean getIsActive() {
            return isActive;
        }

        public String getMembershipType() {
            return membershipType;
        }
        
        // Setters
        public void setName(String name) {
            this.name = name;
        }
        
        public void setIsActive(boolean isActive) {
            this.isActive = isActive;
        }

        public void setMembershipType(String membershipType) {
            this.membershipType = membershipType;
        }
        
        // Returns a boolean value if the membership is active or not
        public boolean activeMember() {
            return isActive;
        }

        // Type of membership lookup. Our library isn’t free
        public double typeMemberFee() {
            switch (membershipType.toLowerCase()) {
                case "student":
                    return 5.0;
                case "adult":
                    return 10.0;
                case "premium":
                    return 20.0;
                default:
                    return 0.0;
            }
        }

        // Returns the information about which books have been loaned and which are past due
        public void bookActivity(String bookTitle) {
            System.out.println(name + " borrowed: " + bookTitle);
        }

        // Return a book
        public void bookActivity(String bookTitle, boolean returning) {
            if (returning) {
                System.out.println(name + " returned: " + bookTitle);
            }
        }

        // View activity
        public void bookActivity() {
            System.out.println(name + " checked their book activity.");
        }
    }
}
