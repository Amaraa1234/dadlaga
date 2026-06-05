import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class task61 {

    public static void main(String[] args) {
        // Scanner-ыг энд зарласнаар автоматаар хаагдана
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.print("orolt file iin ner");
            String inputFileName = scanner.nextLine().trim();

            if (inputFileName.isEmpty()) {
                System.out.println("error file iin ner hooson baij bolohgui.");
                return;
            }

            // BufferedReader болон BufferedWriter-ыг энд зарлана
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
                    BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {

                System.out.println("file aas unshij baina " + inputFileName);

                String line;
                while ((line = reader.readLine()) != null) {
                    writer.write(toPascalCase(line));
                    writer.newLine();
                }

                System.out.println("amjilttai: output.txt-d bichij duuslaa.");

            } catch (FileNotFoundException e) {
                System.out.println("Алдаа: '" + inputFileName + "file oldsongoi");
            } catch (IOException e) {
                System.out.println("error: " + e.getMessage());
            }
        } // Энд scanner автоматаар хаагдана
    }

    private static String toPascalCase(String line) {
        if (line == null || line.trim().isEmpty()) {
            return "";
        }

        // \\W+ нь үсэг, тооноос бусад тэмдэгтээр салгана
        String[] words = line.trim().split("\\W+");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(word.substring(0, 1).toUpperCase());
                result.append(word.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }
}