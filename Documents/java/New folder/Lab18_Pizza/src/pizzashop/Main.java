package pizzashop;

import pizzashop.factory.PizzaFactory;
import pizzashop.model.Pizza;
import pizzashop.decorator.*;
import pizzashop.payment.*;
import pizzashop.logger.OrderLogger;

public class Main {
    public static void main(String[] args) {
        PizzaFactory factory = new PizzaFactory();
        OrderLogger logger = OrderLogger.getInstance();

        // Захиалга 1: Margherita + бяслаг + мөөг, бэлнээр
        Pizza pz1 = new Mushroom(new ExtraCheese(factory.createPizza("margherita")));
        Order o1 = new Order(pz1);
        o1.setPaymentMethod(new CashPayment());
        logger.log("Шинэ захиалга: " + pz1.getName());
        o1.checkout();

        System.out.println("---");

        // Захиалга 2: BBQ + олив + халуун чинжүү, карт
        Pizza pz2 = new Jalapeno(new Olives(factory.createPizza("bbq")));
        Order o2 = new Order(pz2);
        o2.setPaymentMethod(new CardPayment("1234567890123456"));
        logger.log("Шинэ захиалга: " + pz2.getName());
        o2.checkout();

        // Нийт захиалгын тоо
        System.out.println("===");
        System.out.println("Нийт лог бичлэг: " + logger.size());
    }
}