package pizzashop.model;

public class PepperoniPizza implements Pizza {
    @Override
    public String getName() {
        return "Pepperoni";
    }

    @Override
    public double getBasePrice() {
        return 18000;
    }

    @Override
    public String getIngredients() {
        return "Пеперони, моцарелла, улаан лооль";
    }
}