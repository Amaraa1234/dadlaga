package pizzashop.payment;

public class QpayPayment implements PaymentStrategy {
    @Override
    public boolean pay(double amount) {
        System.out.println("QPay QR код үүслээ. " + amount + "₮ уншуулна уу.");
        return true;
    }

    @Override
    public String getName() {
        return "QPay";
    }
}
