package pizzashop.model;

public class BBQChickenPizza implements Pizza {
    @Override
    public String getName() {
        return "BBQ Chicken";
    }

    @Override
    public double getBasePrice() {
        return 22000;
    }

    @Override
    public String getIngredients() {
        return "BBQ тахианы мах, сонгино, моцарелла";
    }
}
