public class Book {
    private String isbn;
    private String title;
    private String author;
    private boolean isAvailable;

    public void updateStatus(boolean status) {}
    public void getDetails() {}
}
import java.util.List;

public class Member {
    private String memberId;
    private String name;
    private List<Book> borrowedBooks;

    public void borrowBook(Book book) {}
    public void returnBook(Book book) {}
}
public class Librarian {
    private String employeeId;
    private String name;

    public void addBook(Book book) {}
    public void registerMember(Member member) {}
    public void issueBook(Book book, Member member) {}
}
import java.util.Date;

public class Transaction {
    private String transactionId;
    private Book book;
    private Member member;
    private Date issueDate;
    private Date dueDate;

    public double calculateFine() {
        return 0.0;
    }
}