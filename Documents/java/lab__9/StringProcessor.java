import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StringProcessor {
    public void processStrings(List<String> strings,
            Predicate<String> filter,
            Function<String, String> transformer,
            Consumer<String> action) {
        strings.stream()
                .filter(filter)
                .map(transformer)
                .forEach(action);
    }

    public static void main(String[] args) {
        StringProcessor processor = new StringProcessor();
        List<String> data = List.of("Java", "Programming", "Code");

        Predicate<String> longWords = s -> s.length() > 5;
        Function<String, String> reverse = s -> new StringBuilder(s).reverse().toString();
        Consumer<String> printer = s -> System.out.println("garshil: " + s.toUpperCase());

        processor.processStrings(data, longWords, reverse, printer);
    }
}