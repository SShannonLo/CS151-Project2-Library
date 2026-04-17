import java.time.Duration; // import Duration built-in function for Java

public class AudioBook extends BookType {


    private String audioId;
    private String title;
    private String author;
    private String isbn;
    private String checkOutById;
    private Duration durationMinutes;



    public String getCheckOutById() {
        return checkOutById;
    }

    public void setCheckOutById(String checkOutById) {
        this.checkOutById = checkOutById;
    }


    public String getAudioId() {
        return audioId;
    }

    public void setAudioId(String audioId) {
        this.audioId = audioId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setDuration(Duration dur){
        this.durationMinutes = dur;
    }

    Duration d = Duration.ofHours(12).plusMinutes(23);



    public AudioBook(String audioId, String title, String author, String isbn, long hours, long min){
        if(bookCount >= MAX_COUNT){
            throw new IllegalArgumentException("Maximum book limit reached");

        }
        setAudioId(audioId);
        setTitle(title);
        setAuthor(author);
        setIsbn(isbn);
        setDuration(Duration.ofHours(hours).plusMinutes(min));

        bookCount++;

    }

    private static int bookCount = 0;
    public static final int MAX_COUNT = 100;

    public String getFormattedDuration() {
        long hours = durationMinutes.toHours();
        long mins = durationMinutes.toMinutesPart();
        return String.format("%dh %02dm", hours, mins);
    }


}
