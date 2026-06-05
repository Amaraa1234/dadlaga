import java.util.*;

class Student {
    private String name;
    private String code;
    private double gpa;

    public Student(String name, String code, double gpa) {
        this.name = name;
        this.code = code;
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return String.format("   Oyuutan: %s (Kod: %s, GPA: %.1f)", name, code, gpa);
    }
}

class Teacher implements Comparable<Teacher> {
    private String name;
    private String department;
    private ArrayList<Student> s = new ArrayList<>();

    public Teacher(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public void addStudent(Student student) {
        s.add(student);
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Teacher other) {
        // Nerere erembelkh logik
        return this.name.compareTo(other.name);
    }

    public void displayInfo() {
        System.out.println("Bagsh: " + name + " (Salbar: " + department + ")");
        for (Student student : s) {
            System.out.println(student);
        }
    }
}

public class Test {
    public static void main(String[] args) {
        LinkedList<Teacher> obj = new LinkedList<>();

        // Bagsh bolon oyuutny ugogdol uusgekh
        Teacher t1 = new Teacher("Bat", "Computer Science");
        t1.addStudent(new Student("Bold", "B2101", 3.8));
        t1.addStudent(new Student("Sarnai", "B2105", 3.5));

        Teacher t2 = new Teacher("Anar", "Physics");
        t2.addStudent(new Student("Tsetseg", "P2109", 3.9));

        obj.add(t1);
        obj.add(t2);

        // Erembelekhees omnokh
        System.out.println("--- Erembelekhees omnokh ---");
        for (Teacher t : obj)
            t.displayInfo();

        // Collections.sort ashiglan nerere erembelne
        Collections.sort(obj);

        // Erembelsnii daraakh
        System.out.println("\n--- Nereer erembelsnii daraakh ---");
        for (Teacher t : obj)
            t.displayInfo();
    }
}
