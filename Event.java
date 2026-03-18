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
}