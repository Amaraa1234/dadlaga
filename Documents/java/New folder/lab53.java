import java.util.ArrayList;
import java.util.Random;

public class lab53 {
    public static void main(String[] args) {
        Random rand = new Random();
        int length = rand.nextInt(100) + 1;
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            list.add(rand.nextInt(100));
        }

        System.out.println(list);

        double sum = 0;
        for (int num : list) {
            sum += num;
        }

        double average = sum / list.size();
        list.removeIf(n -> n < average);

        for (int num : list) {
            System.out.print(num + " ");
        }
    }
}