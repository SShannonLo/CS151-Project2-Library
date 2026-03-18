public class Member {
    private String memberId;
    private String name;
    private String phoneNumber;
    private double fineAmount;

    public Member(String memberId, String name, String phoneNumber) {
        this.memberId = memberId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.fineAmount = 0.0;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public void addFine(double amount) {
        fineAmount += amount;
    }
}
