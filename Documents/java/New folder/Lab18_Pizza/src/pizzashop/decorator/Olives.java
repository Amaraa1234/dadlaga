package pizzashop.decorator;

import pizzashop.model.Pizza;

public class Olives extends PizzaDecorator {
    public Olives(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getName() {
        return pizza.getName() + " + оливт";
    }

    @Override
    public double getBasePrice() {
        return pizza.getBasePrice() + 1000;
    }

    @Override
    public String getIngredients() {
        return pizza.getIngredients() + ", оливт";
    }
}