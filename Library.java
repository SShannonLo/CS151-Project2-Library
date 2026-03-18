public class Library {

    // Inner Member class
    public static class Member {
        private String name;
        private boolean isActive;
        private String membershipType;

        // Constructor
        public Member(String name, boolean isActive, String membershipType) {
            this.name = name;
            this.isActive = isActive;
            this.membershipType = membershipType;
        }

        // Returns if membership is active
        public boolean activeMember() {
            return isActive;
        }

        // Returns membership fee based on type
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

        // Borrow a book
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
