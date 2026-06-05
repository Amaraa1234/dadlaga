import java.util.ArrayList;
import java.util.Date;

class Product {
    private String name;
    private String barcode;
    private double price;

    public Product(String name, String barcode, double price) {
        this.name = name;
        this.barcode = barcode;
        setPrice(price);
    }

    public String getName() {
        return name;
    }

    public String getBarcode() {
        return barcode;
    }

    public double getPrice() {
        return price;
    }

    // Setters
    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        } else {
            System.out.println("Error: Price cannot be negative!");
        }
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Customer {
    private String name;
    private int phone;

    public Customer(String name, int phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public int getPhone() {
        return phone;
    }

    // Customer дээр нэмсэн Setter-үүд
    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}

class DiscountCard {
    private int barcode;
    private Customer customer;
    private double discountPercent;

    public DiscountCard(int barcode, Customer customer, double discountPercent) {
        this.barcode = barcode;
        this.customer = customer;
        this.discountPercent = discountPercent;
    }

    public int getBarcode() {
        return barcode;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    // DiscountCard дээр нэмсэн Setter
    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }
}

class Employee {
    private String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Employee дээр нэмсэн Setter
    public void setName(String name) {
        this.name = name;
    }
}

class SaleItem {
    private Product product;
    private int quantity;
    private double soldPrice;

    public SaleItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.soldPrice = product.getPrice();
    }

    public double getLineTotal() {
        return quantity * soldPrice;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}

class SaleOrder {
    private Employee employee;
    private Date date;
    private ArrayList<SaleItem> items;
    private DiscountCard discountCard;

    public SaleOrder(Employee employee) {
        this.employee = employee;
        this.date = new Date();
        this.items = new ArrayList<>();
    }

    public void addItem(Product product, int qty) {
        items.add(new SaleItem(product, qty));
    }

    public void setDiscountCard(DiscountCard card) {
        this.discountCard = card;
    }

    public void printReceipt() {
        double subTotal = 0;
        System.out.println("\n********** SALES RECEIPT **********");
        System.out.println("Date: " + date);
        System.out.println("Cashier: " + employee.getName());

        if (discountCard != null) {
            System.out.println("Customer: " + discountCard.getCustomer().getName());
        }
        System.out.println("-----------------------------------");

        for (SaleItem item : items) {
            double total = item.getLineTotal();
            subTotal += total;
            System.out.printf("%-15s x%d \t %.2f MNT\n",
                    item.getProduct().getName(), item.getQuantity(), total);
        }

        double discountAmount = 0;
        if (discountCard != null) {
            discountAmount = subTotal * (discountCard.getDiscountPercent() / 100);
        }

        double taxableAmount = subTotal - discountAmount;
        double vat = taxableAmount * 0.1;
        double finalTotal = taxableAmount + vat;

        System.out.println("-----------------------------------");
        System.out.printf("Subtotal: \t\t %.2f MNT\n", subTotal);
        System.out.printf("Discount (%s%%): \t -%.2f MNT\n",
                (discountCard != null ? discountCard.getDiscountPercent() : 0), discountAmount);
        System.out.printf("VAT (10%%): \t\t %.2f MNT\n", vat);
        System.out.printf("TOTAL AMOUNT: \t\t %.2f MNT\n", finalTotal);
        System.out.println("***********************************\n");
    }
}

public class POSSystem {
    public static void main(String[] args) {
        // 1. Бүтээгдэхүүн үүсгэх
        Product milk = new Product("Milk", "865001", 3500.0);
        Product bread = new Product("Bread", "865002", 2200.0);

        milk.setPrice(3800.0);
        bread.setName("Premium Bread");

        Employee cashier = new Employee("Bat-Erdene");
        cashier.setName("Anand (Updated Cashier)");

        Customer client = new Customer("Saraa", 99001122);
        client.setName("Saraa (VIP)");

        DiscountCard card = new DiscountCard(123, client, 3.0);
        card.setDiscountPercent(5.0);

        SaleOrder order = new SaleOrder(cashier);
        order.addItem(milk, 2);
        order.addItem(bread, 1);
        order.setDiscountCard(card);

        order.printReceipt();
    }
}