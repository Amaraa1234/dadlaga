import java.util.Arrays;
import java.util.Random;

public class lab5 {
    public static void main(String[] args) {
        Random rand = new Random();
        int length = rand.nextInt(100) + 1;
        int[] arr = new int[length];

        for (int i = 0; i < length; i++) {
            arr[i] = rand.nextInt(1000);
        }

        System.out.println(Arrays.toString(arr));

        Arrays.sort(arr);

        System.out.println(Arrays.toString(arr));
    }
}