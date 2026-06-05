package pizzashop.factory;

import pizzashop.model.*;

public class PizzaFactory {
    public Pizza createPizza(String type) {
        switch (type.toLowerCase()) {
            case "margherita":
                return new MargheritaPizza();
            case "pepperoni":
                return new PepperoniPizza();
            case "veggie":
                return new VeggiePizza();
            case "bbq":
                return new BBQChickenPizza();
            default:
                throw new IllegalArgumentException("Мэдэгдэхгүй пицца: " + type);
        }
    }
}