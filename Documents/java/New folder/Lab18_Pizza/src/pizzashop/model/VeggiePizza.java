package pizzashop.model;

public class VeggiePizza implements Pizza {
    @Override
    public String getName() {
        return "Veggie";
    }

    @Override
    public double getBasePrice() {
        return 16000;
    }

    @Override
    public String getIngredients() {
        return "Мөөг, оливт, чинжүү, улаан лооль";
    }
}
