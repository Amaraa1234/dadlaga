package pizzashop;

import pizzashop.model.Pizza;
import pizzashop.payment.PaymentStrategy;
import pizzashop.logger.OrderLogger;
import pizzashop.observer.OrderObserver;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private Pizza pizza;
    private PaymentStrategy payment;
    private List<OrderObserver> observers = new ArrayList<>();

    // Конструктор
    public Order(Pizza pizza) {
        this.pizza = pizza;
    }

    // Пиццаг буцаах getter
    public Pizza getPizza() {
        return pizza;
    }

    // Төлбөрийн аргыг тохируулах setter
    public void setPaymentMethod(PaymentStrategy p) {
        this.payment = p;
    }

    // Төлөвлөгч (Observer) бүртгэх
    public void registerObserver(OrderObserver observer) {
        observers.add(observer);
    }

    // Захиалгыг баталгаажуулж төлбөр авах
    public void checkout() {
        double total = pizza.getBasePrice();
        System.out.println("Захиалга: " + pizza.getName());
        System.out.println("Үнэ: " + total + "₮");

        if (payment == null) {
            throw new IllegalStateException("Төлбөрийн арга сонгоогүй");
        }

        boolean success = payment.pay(total);

        if (success) {
            OrderLogger logger = OrderLogger.getInstance();
            logger.log("Шинэ захиалга: " + pizza.getName());
            logger.log("Төлбөр: " + payment.getName() + " — " + total + "₮");
            logger.log("Захиалга амжилттай боллоо");

            // Бүртгэлтэй бүх Observer-уудад мэдэгдэх (Гал тогоо, Хүргэлт)
            for (OrderObserver observer : observers) {
                observer.onNewOrder(this);
            }
        }
    }
}