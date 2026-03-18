public class Event {
    private String type;
    private String date;
    private String location;
    private String topic;
    private String organizer;

    public Event(String type, String date, String location, String topic, String organizer) {
        this.type = type;
        this.date = date;
        this.location = location;
        this.topic = topic;
        this.organizer = organizer;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public String getTopic() {
        return topic;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public void authorTalk(String authorName, String bookTitle) {
    System.out.println("Author Talk by " + authorName + " about " + bookTitle);
    }

    public void writingWorkshop(String instructor, int durationMinutes) {
    System.out.println("Workshop by " + instructor + " lasting " + durationMinutes + " minutes");
    }

    public void communityMeeting(String topic) {
    System.out.println("Community meeting about " + topic);
    }

    public void storyTimeForKids(String storyTitle, int ageGroup) {
    System.out.println("Story: " + storyTitle + " for age " + ageGroup);
    }

}
