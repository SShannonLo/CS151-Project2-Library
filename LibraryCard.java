public class LibraryCard {
    private String cardId;
    private Member member;
    private boolean isActive;

    public LibraryCard(String cardId, Member member) {
        this.cardId = cardId;
        this.member = member;
        this.isActive = false;
    }

    public void activateCard() {
        isActive = true;
        System.out.println("Card activated.");
    }

    public void deactivateCard() {
        isActive = false;
        System.out.println("Card deactivated.");
    }

    public boolean checkValidity() {
        return isActive;
    }

    public void renewCard() {
        System.out.println("Card renewed.");
    }
}