import java.util.*;
import java.time.LocalDate;

class Product {
    String id;
    String name;

    public Product(String id, String name) {
        this.id = id;
        this.name = name;
    }
}

class Transaction {
    Product product;
    int amount;
    LocalDate date;
    boolean isIncome; // true бол орлого, false бол зарлага

    public Transaction(Product product, int amount, LocalDate date, boolean isIncome) {
        this.product = product;
        this.amount = amount;
        this.date = date;
        this.isIncome = isIncome;
    }
}

class Warehouse {
    String name;
    String accountantName; // Нярав
    Map<String, Integer> stock = new HashMap<>();
    List<Transaction> history = new ArrayList<>();

    public Warehouse(String name, String accountantName) {
        this.name = name;
        this.accountantName = accountantName;
    }

    public void receiveProduct(Product p, int qty, LocalDate date) {
        stock.put(p.id, stock.getOrDefault(p.id, 0) + qty);
        history.add(new Transaction(p, qty, date, true));
        System.out.println("orlogo: " + p.name + " +" + qty);
    }

    public void releaseProduct(Product p, int qty, LocalDate date) {
        int current = stock.getOrDefault(p.id, 0);
        if (current >= qty) {
            stock.put(p.id, current - qty);
            history.add(new Transaction(p, qty, date, false));
            System.out.println("zarlaga: " + p.name + " -" + qty);
        } else {
            System.out.println("error uldegdel hureltsehgui baina!");
        }
    }

    public void printStockReport() {
        System.out.println("\n--- nootsiin tailan(" + name + ") ---");
        for (String id : stock.keySet()) {
            System.out.println("baraa ID: " + id + " | uldegdel: " + stock.get(id));
        }
    }

    public void inventoryCheck(Product p, int actualQty, LocalDate date) {
        int expectedQty = stock.getOrDefault(p.id, 0);
        int diff = actualQty - expectedQty;

        if (diff > 0) {
            System.out.println("toollogiin iluudel: " + diff);
        } else if (diff < 0) {
            System.out.println("toollogiin dutagdal: " + diff);
        }

        stock.put(p.id, actualQty); // Үлдэгдлийг шинэчлэх
        System.out.println(p.name + " baraanii uldegdel " + actualQty + " bolj shichlegdlee.");
    }
}

public class Main {
    public static void main(String[] args) {
        Warehouse myWarehouse = new Warehouse("tov aguulah", "Bat");
        Product p1 = new Product("A01", "Apple iPhone 15");

        // Гүйлгээ хийх
        myWarehouse.receiveProduct(p1, 50, LocalDate.now());
        myWarehouse.releaseProduct(p1, 10, LocalDate.now());

        myWarehouse.printStockReport();

        myWarehouse.inventoryCheck(p1, 38, LocalDate.now());
    }
}