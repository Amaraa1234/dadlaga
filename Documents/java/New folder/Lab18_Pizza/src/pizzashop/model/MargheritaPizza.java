package pizzashop.model;

public class MargheritaPizza implements Pizza {
    @Override
    public String getName() {
        return "Margherita";
    }

    @Override
    public double getBasePrice() {
        return 15000;
    }

    @Override
    public String getIngredients() {
        return "Улаан лооль, мозарелла, базилик";
    }
}
