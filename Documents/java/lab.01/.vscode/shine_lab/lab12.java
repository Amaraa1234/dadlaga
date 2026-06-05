abstract class Shape {
    String color;
    public double s; // Talbaig khadgalakh khuvsagch

    Shape(String color) {
        this.color = color;
    }

    // Khiisver funktsuud - Khuu klassuud zaaval dahin todorhoilokh yostoi
    abstract void square();

    abstract void show();
}

// 2. Kvadrat klass (Quadrate)
class Quadrate extends Shape {
    private int a;

    Quadrate(String color, int a) {
        super(color);
        this.a = a;
    }

    @Override
    void square() {
        this.s = a * a;
    }

    @Override
    void show() {
        System.out.println("--- Kvadrat ---");
        System.out.println("Ungu: " + color);
        System.out.println("Talbai: " + s);
    }

    int retA() {
        return a;
    }
}

// 3. Tegsh untsugt klass (Rectangle)
class Rectangle extends Shape {
    private int a, b;
    private int perimeter;

    Rectangle(String color, int a, int b) {
        super(color);
        this.a = a;
        this.b = b;
    }

    @Override
    void square() {
        this.s = a * b;
    }

    // Perimetr oloh nemelt funkts
    void calculatePerimeter() {
        this.perimeter = 2 * (a + b);
    }

    @Override
    void show() {
        System.out.println("--- Tegsh untsugt ---");
        System.out.println("Ungu: " + color);
        System.out.println("Talbai: " + s);
        System.out.println("Perimetr: " + perimeter);
    }
}

// 4. Toirog klass (Circle)
class Circle extends Shape {
    private int r;

    Circle(String color, int r) {
        super(color);
        this.r = r;
    }

    @Override
    void square() {
        this.s = Math.PI * r * r;
    }

    @Override
    void show() {
        System.out.println("--- Toirog ---");
        System.out.println("Ungu: " + color);
        System.out.printf("Talbai: %.2f\n", s);
    }
}

public class lab12 {
    public static void main(String[] args) {

        Quadrate q = new Quadrate("Ulaan", 5);
        q.square();
        q.show();

        Rectangle r = new Rectangle("nogoon", 4, 6);
        r.square();
        r.calculatePerimeter();
        r.show();

        Circle c = new Circle("Shar", 3);
        c.square();
        c.show();
    }
}