package pizzashop.payment;

public class CashPayment implements PaymentStrategy {
    @Override
    public boolean pay(double amount) {
        System.out.println("Бэлнээр " + amount + "₮ төлөгдлөө.");
        return true;
    }

    @Override
    public String getName() {
        return "Бэлэн";
    }
}
