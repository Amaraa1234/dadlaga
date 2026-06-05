import java.util.*;
import java.time.LocalDate;

// 1. Бараа
class Product {
    private String id;
    private String name;
}

// 2. Нярав
class Storekeeper {
    private String id;
    private String name;
}

// 3. Падаан (Орлого болон зарлагын суурь класс)
abstract class Invoice {
    protected String invoiceNo;
    protected LocalDate date;
    protected String personInCharge; // Хүлээлгэн өгсөн эсвэл хүлээн авсан хүн
    protected List<InvoiceItem> items;

    public abstract void printInvoice();
}

class InvoiceItem {
    Product product;
    int quantity;
}

// 4. Орлогын падаан
class IncomeInvoice extends Invoice {
    @Override
    public void printInvoice() {
        /* Хэрэгжүүлэлт */ }
}

// 5. Зарлагын падаан
class OutcomeInvoice extends Invoice {
    @Override
    public void printInvoice() {
        /* Хэрэгжүүлэлт */ }
}

// 6. Агуулах
class Warehouse {
    private String warehouseName;
    private Storekeeper storekeeper;
    private Map<String, Integer> currentStock;
    private List<Invoice> allInvoices;

    // Шаардлага: Нөөцийн тайлан харах (Бүх бараагаар эсвэл сонгосон бараагаар)
    public void viewStockReport(List<Product> selectedProducts) {
    }

    // Шаардлага: Няравын тайлан (Огноогоор шүүх)
    public void viewAccountantReport(LocalDate start, LocalDate end, List<Product> products) {
    }

    // Шаардлага: Тооллого хийх
    public void performAudit(Product product, int actualQty, LocalDate date) {
    }
}