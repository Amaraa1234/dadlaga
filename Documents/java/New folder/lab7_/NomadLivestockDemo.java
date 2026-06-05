import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

abstract class Livestock {
    private String name;
    private int age;

    public Livestock(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    abstract String makeSound();
}

interface WorkRole {
    String performTask();
}

class Horse extends Livestock implements WorkRole {
    public Horse(String name, int age) {
        super(name, age);
    }

    @Override
    String makeSound() {
        return "yantsgaana!";
    }

    @Override
    public String performTask() {
        return "mori unalgand hereglegdene.";
    }
}

class Sheep extends Livestock {
    public Sheep(String name, int age) {
        super(name, age);
    }

    @Override
    String makeSound() {
        return "maylna!";
    }
}

class Camel extends Livestock implements WorkRole {
    public Camel(String name, int age) {
        super(name, age);
    }

    @Override
    String makeSound() {
        return "builna!";
    }

    @Override
    public String performTask() {
        return "temee achlaga, teevert hereglegdene.";
    }
}

class Herd {
    private final ArrayList<Livestock> livestockList = new ArrayList<>();

    public void addLivestock(Livestock animal) {
        livestockList.add(animal);
    }

    public void dailyRoutine(PrintWriter out) {
        out.println("====== SUREGIIN ODOR TUTMYN TOLOV ======");
        for (Livestock animal : livestockList) {
            out.printf("%s (%s): %s\n", animal.getName(), animal.getClass().getSimpleName(), animal.makeSound());
            if (animal instanceof WorkRole) {
                out.printf("   -> Uureg: %s\n", ((WorkRole) animal).performTask());
            }
        }
        out.println("=======================================");
    }
}

public class NomadLivestockDemo {
    public static void main(String[] args) {
        Herd herd = new Herd();
        Scanner consoleScanner = new Scanner(System.in);

        // 1. Хэрэглэгчээс унших файлын нэрийг асуух
        System.out.print("Unshih failyn neriig oruulna uu (Jishee ni: input.txt): ");
        String inputFileName = consoleScanner.nextLine().trim();

        // 2. Бага элементийн хязгаарлалтыг асуух
        System.out.print("Failaas unshih moriin hamgiin baga elementiin toog oruulna uu (Jishee ni 3): ");
        int minElements;
        try {
            minElements = Integer.parseInt(consoleScanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("[ALDAA] Zovhon buhel too oruulna uu!");
            return;
        }

        // Хэрэглэгчийн оруулсан нэрээр файлыг үүсгэж шалгах
        File inputFile = new File(inputFileName);
        if (!inputFile.exists()) {
            System.out.println("[ALDAA] '" + inputFileName + "' fail oldsongui! Neree zov checklene uu.");
            return;
        }

        // Файлаас өгөгдөл унших хэсэг
        try (Scanner fileScanner = new Scanner(inputFile)) {
            int loadedCount = 0;

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.isEmpty())
                    continue;

                String[] parts = line.split("\\s+");

                if (parts.length >= minElements) {
                    if (parts.length < 3) {
                        System.out.println("[ANHAARUULGA] Ogogdol dutuu (Tuurul, Ner, Nas shaardlagatai): " + line);
                        continue;
                    }

                    try {
                        String type = parts[0].toLowerCase();
                        String name = parts[1];
                        int age = Integer.parseInt(parts[2]);

                        Livestock animal = null;
                        switch (type) {
                            case "horse" -> animal = new Horse(name, age);
                            case "sheep" -> animal = new Sheep(name, age);
                            case "camel" -> animal = new Camel(name, age);
                            default -> System.out.println("Tanigdahgui tuurul algaslaa: " + type);
                        }

                        if (animal != null) {
                            herd.addLivestock(animal);
                            loadedCount++;
                        }
                    } catch (NumberFormatException nfe) {
                        System.out.println("Nasny format buruu baina (Too baih yostoi): " + line);
                    }
                } else {
                    System.out.println("Mor algaslaa (Urt hurehgui baina): " + line);
                }
            }
            System.out.println("\nFailaas niit " + loadedCount + " amytny medeelel amjilttai unshlaa.");
        } catch (Exception e) {
            System.out.println("Fail unshih yavcat aldaa garlaa: " + e.getMessage());
        }

        // 3. Гаралтын файлын нэрийг уян хатан болгох
        // Хэрэв "data.txt" гэж оруулсан бол үр дүн нь "data_output.txt" нэртэй гарна.
        String outputFileName = "output.txt";
        if (inputFileName.contains(".")) {
            outputFileName = inputFileName.substring(0, inputFileName.lastIndexOf(".")) + "_output.txt";
        } else {
            outputFileName = inputFileName + "_output.txt";
        }

        // Үр дүнг файл руу бичих
        try (PrintWriter writer = new PrintWriter(outputFileName)) {
            herd.dailyRoutine(writer);
            System.out.println("[AMJILTTAI] Ur dung '" + outputFileName + "' fail ruu bichlee. Shalgana uu.");
        } catch (Exception e) {
            System.out.println("Faild ur dung bichihed aldaa garlaa.");
        }
    }
}