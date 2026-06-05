package pizzashop.payment;

public interface PaymentStrategy {
    boolean pay(double amount);

    String getName();
}
