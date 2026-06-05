import java.util.ArrayList;

class Product {
    String name;
    double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class SaleLine {
    Product product;
    int qty;

    SaleLine(Product product, int qty) {
        this.product = product;
        this.qty = qty;
    }

    double getLineTotal() {
        return product.price * qty;
    }
}

public class Main {
    public static void main(String[] args) {
        Product p1 = new Product("talh", 1800.0);
        Product p2 = new Product("milk", 4200.0);
        ArrayList<SaleLine> cart = new ArrayList<>();

        cart.add(new SaleLine(p1, 3));
        cart.add(new SaleLine(p2, 2));

        double total = 0;
        System.out.println("delguuriin talon");

        for (SaleLine line : cart) {
            double linePrice = line.getLineTotal();
            System.out.println(line.product.name + " x " + line.qty + " = " + linePrice + "$");
            total += linePrice;
        }

        System.out.println("---------------------------");
        System.out.println("niit dun: " + total + "$");
        System.out.println("NOUT (3%): " + (total * 0.03) + "$");
        System.out.println("TOLOH DUN: " + (total * 1.1) + "$");
    }
}