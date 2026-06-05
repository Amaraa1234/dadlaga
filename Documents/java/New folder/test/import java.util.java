import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class Library {
    private List<Book> books = new ArrayList<>();

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
}