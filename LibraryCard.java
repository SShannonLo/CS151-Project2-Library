public class LibraryCard {
    private String cardId;
    private Member member;
    private String expirationDate;

    public LibraryCard(String cardId, Member member, String expirationDate) {
        this.cardId = cardId;
        this.member = member;
        this.expirationDate = expirationDate;
    }

    public void activateCard() {
        System.out.println("Card activated.");
    }

    public void deactivateCard() {
        System.out.println("Card deactivated.");
    }

    public boolean checkValidity() {
        return true;
    }
    
    public void renewCard(String newExpirationDate) {
        this.expirationDate = newExpirationDate;
        System.out.println("Card renewed until " + expirationDate);
    } 

    public String getCardId() {
        return cardId;
    }

    public Member getMember() {
        return member;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

}
