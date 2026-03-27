import java.util.ArrayList;

public class Catalog{
    private static int catalogCount = 0;
    public static final int MAX_COUNT = 100;
    private String catalogName;
    private ArrayList<Book> books;

    public Catalog(String catalogName){
        if(catalogCount >= MAX_COUNT){
            throw new IllegalStateException("Maximum catalog limit reached");
        }
        this.catalogName = catalogName;
        this.books = new ArrayList<>();
        catalogCount++;
    }
    
    public boolean addBook(Book book){
        if(book == null){
            System.out.println("Invalid, book cannot be null");
            return false;
        }
        if(books.contains(book)){
            System.out.println("Invalid, book already exists in the catalog");
            return false;
        }
        books.add(book);
        System.out.println("Book added to catalog successfully");
        return true;
    }

     public boolean removeBook(Book book) {
        if (book == null) {
            System.out.println("Remove failed: book cannot be null.");
            return false;
        }
        if (!books.contains(book)) {
            System.out.println("Remove failed: book not found in catalog.");
            return false;
        }
        books.remove(book);
        System.out.println("Book removed from catalog.");
        return true;
    }

    public ArrayList<Book> searchByTitle(String title){
        ArrayList<Book> result = new ArrayList<>();
        if(title == null || title.trim().isEmpty()){
            System.out.println("Invalid, title cannot be null or empty");
            return result;
        }
        for(Book book : books){
            if(book.getTitle().equalsIgnoreCase(title)){
                result.add(book);
            }
        }
        return result;
    }

    public ArrayList<Book> searchByAuthor(String author) {
        ArrayList<Book> result = new ArrayList<>();
        if (author == null || author.trim().isEmpty()) {
            System.out.println("Invalid, author cannot be null or empty");
            return result;
        }
        for (Book book : books) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    public void displayAllBooks(){
        if(book.isEmpty()){
            System.out.println("No books in catalog");
            return;
        }
        for(Book book : books){
            System.out.println(book);
        }        
    }

    public int countAvailableBooks(){
        int count = 0;
        for(Book book : books){
            if(book.isAvailable()){
                count++;
            }
        }
        return count;
    }
    
     public Book searchByBookId(String bookId) {
        for (Book book : books) {
            if (book.getBookId().equalsIgnoreCase(bookId)) {
                return book;
            }
        }
        return null;
    }


    public String getCatalogName(){
        return catalogName;
    }

    public void setCatalogName(String catalogName){
        if(catalogName == null || catalogName.trim().isEmpty()){
            throw new IllegalArgumentException("Catalog name cannot be null or empty");
        }
        this.catalogName = catalogName;
    }

    public ArrayList<Book> getBooks(){
        return books;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "catalogName='" + catalogName + '\'' +
                ", totalBooks=" + books.size() +
                '}';
    }
}