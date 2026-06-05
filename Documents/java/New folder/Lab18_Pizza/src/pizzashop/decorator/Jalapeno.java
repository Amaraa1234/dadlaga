package pizzashop.decorator;

import pizzashop.model.Pizza;

public class Jalapeno extends PizzaDecorator {
    public Jalapeno(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getName() {
        return pizza.getName() + " + халуун чинжүү";
    }

    @Override
    public double getBasePrice() {
        return pizza.getBasePrice() + 1200;
    }

    @Override
    public String getIngredients() {
        return pizza.getIngredients() + ", халуун чинжүү";
    }
}
