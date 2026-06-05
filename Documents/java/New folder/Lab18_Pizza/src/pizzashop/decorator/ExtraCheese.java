package pizzashop.decorator;

import pizzashop.model.Pizza;

public class ExtraCheese extends PizzaDecorator {
    public ExtraCheese(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getName() {
        return pizza.getName() + " + нэмэлт бяслаг";
    }

    @Override
    public double getBasePrice() {
        return pizza.getBasePrice() + 2000;
    }

    @Override
    public String getIngredients() {
        return pizza.getIngredients() + ", нэмэлт бяслаг";
    }
}
