import java.util.ArrayList;
import java.util.List;

class Animal {
    String name;

    Animal(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

class Dog extends Animal {
    Dog(String name) {
        super(name);
    }
}

public class Zoo<T extends Animal> {

    public void transferAnimals(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
        source.clear();
    }

    public static void main(String[] args) {
        Zoo<Animal> myZoo = new Zoo<>();

        List<Animal> sourceList = new ArrayList<>();
        sourceList.add(new Animal("Arslan"));
        sourceList.add(new Animal("Togoruu"));

        List<Animal> animalShelter = new ArrayList<>();

        System.out.println("Shiljuulehees omnoh: " + sourceList);

        myZoo.transferAnimals(sourceList, animalShelter);

        System.out.println("Shiljuulsnii daraa (source): " + sourceList);
        System.out.println("Shiljuulsnii daraa (shelter): " + animalShelter);
    }
}