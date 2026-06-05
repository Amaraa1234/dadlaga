import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

// 1. Book klassiig zaaval todorhoiloh shaardlagatai
class Book {
    private String title;
    private int year;

    public Book(String title, int year) {
        this.title = title;
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return title + " (" + year + ")";
    }
}

public class Library {
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    // BookShelf klass (Inner class)
    public class BookShelf implements Iterable<Book> {
        private int minYear;

        public BookShelf(int minYear) {
            this.minYear = minYear;
        }

        @Override
        public Iterator<Book> iterator() {
            return books.stream()
                    .filter(book -> book.getYear() > minYear)
                    .iterator();
        }
    }

    // Programmiig ajilluulah main method
    public static void main(String[] args) {
        Library lib = new Library();
        lib.addBook(new Book("Java Basics", 2010));
        lib.addBook(new Book("Advanced Java", 2024));
        lib.addBook(new Book("Spring Boot", 2022));

        // BookShelf uusgeh (Inner class uchraas lib-eer damjuulna)
        Library.BookShelf shelf = lib.new BookShelf(2020);

        System.out.println("2020 onoos hoishih nomnuud:");
        for (Book book : shelf) {
            System.out.println(book);
        }
    }
}