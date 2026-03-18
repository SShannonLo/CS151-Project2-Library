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

    }
}
