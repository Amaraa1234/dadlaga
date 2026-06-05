import java.util.ArrayList;
import java.util.Scanner;

// 1. Абстракт Класс: Livestock (Мал)
abstract class Livestock {
    String name;
    int age;

    public Livestock(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Абстракт арга (биегүй тул ; -ээр төгсөнө)
    abstract String makeSound();

    void graze() {
        System.out.println(name + " талбайд бэлчинэ.");
    }

    void graze(String food) {
        System.out.println(name + " " + food + "-ыг идэж байна.");
    }
}

// 2. Интерфейс: WorkRole (Ажлын Үүрэг)
interface WorkRole {
    String performTask();
}

// 3. Тодорхой Классууд
class Horse extends Livestock implements WorkRole {
    public Horse(String name, int age) {
        super(name, age);
    }

    @Override
    String makeSound() {
        return "Янцгаана!";
    }

    @Override
    public String performTask() {
        return "Морь талбайд уналгад хэрэглэгдэнэ.";
    }
}

class Sheep extends Livestock {
    public Sheep(String name, int age) {
        super(name, age);
    }

    @Override
    String makeSound() {
        return "Маа!";
    }
}

class Camel extends Livestock implements WorkRole {
    public Camel(String name, int age) {
        super(name, age);
    }

    @Override
    String makeSound() {
        return "Буйлна!";
    }

    @Override
    public String performTask() {
        return "Тэмээ говийн тээвэрт хэрэглэгдэнэ.";
    }
}

// 4. Сүрэг класс
class Herd {
    ArrayList<Livestock> livestockList = new ArrayList<>();

    void addLivestock(Livestock animal) {
        livestockList.add(animal);
    }

    void dailyRoutine() {
        System.out.println("\n--- Сүргийн өдөр тутмын ажил ---");
        for (Livestock animal : livestockList) {
            System.out.println(animal.name + " (" + animal.age + " настай): " + animal.makeSound());
            if (animal instanceof WorkRole) {
                System.out.println("-> " + ((WorkRole) animal).performTask());
            }
            animal.graze();
        }
    }
}

// 5. Үндсэн класс (Файлын нэр үүнтэй ижил байна)
public class NomadLivestockDemo {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Herd myHerd = new Herd();

        System.out.print("Хэдэн мал бүртгэх вэ? ");
        int n = input.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("\n" + (i + 1) + "-р мал:");
            System.out.print("Төрөл (1: Морь, 2: Хонь, 3: Тэмээ): ");
            int type = input.nextInt();
            input.nextLine(); // Хоосон зай цэвэрлэх

            System.out.print("Нэр: ");
            String name = input.nextLine();
            System.out.print("Нас: ");
            int age = input.nextInt();

            if (type == 1)
                myHerd.addLivestock(new Horse(name, age));
            else if (type == 2)
                myHerd.addLivestock(new Sheep(name, age));
            else if (type == 3)
                myHerd.addLivestock(new Camel(name, age));
            else
                System.out.println("Буруу сонголт!");
        }

        myHerd.dailyRoutine();
        input.close();
    }
}