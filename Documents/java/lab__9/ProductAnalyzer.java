import java.util.*;
import java.util.stream.Collectors;

class Product {
    private String name;
    private String category;
    private double price;

    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%s (%.0f)", name, price);
    }
}

public class ProductAnalyzer {
    // Stream ashiglan categor-oor ni grupleh funkts
    public Map<String, List<Product>> analyze(List<Product> products) {
        return products.stream()
                .filter(p -> p.getPrice() > 1000)
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .collect(Collectors.groupingBy(Product::getCategory));
    }

    // Programmiig ajilluulah main method
    public static void main(String[] args) {
        ProductAnalyzer analyzer = new ProductAnalyzer();

        // Test ugogdol uusgeh
        List<Product> list = new ArrayList<>();
        list.add(new Product("Laptop", "Electronics", 2500));
        list.add(new Product("Mouse", "Electronics", 50)); // Filtreer hasagdana ( < 1000)
        list.add(new Product("Table", "Furniture", 1200));
        list.add(new Product("Phone", "Electronics", 1800));

        // Analiz hiih
        Map<String, List<Product>> result = analyzer.analyze(list);

        // Ur dung hevleh
        System.out.println("Analiziin ur dun (Price > 1000):");
        result.forEach((category, productList) -> {
            System.out.println(category + ": " + productList);
        });
    }
}