public class Dugui {

    int diameter;
    String color;
    String material;

    public Dugui(int diameter, String color, String material) {
        this.diameter = diameter;
        this.color = color;
        this.material = material;
    }

    // Эргэх үйлдэл
    public void ergene() {
        System.out.println("Дугуй эргэж байна.");
    }

    // Хөдөлж эхлэх үйлдэл
    public void huduljBaina() {
        System.out.println("Дугуй хөдөлгөөнд орлоо.");
    }

    // Зогсох үйлдэл
    public void zogsoh() {
        System.out.println("Дугуй зогслоо.");
    }
}