import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class lab52 {
    public static void main(String[] args) {
        Random rand = new Random();
        int n = rand.nextInt(100);
        int m = rand.nextInt(100);

        Double[] z = new Double[n + m];

        for (int i = 0; i < n; i++) {
            z[i] = rand.nextDouble() * 100;
        }

        for (int i = 0; i < m; i++) {
            z[n + i] = rand.nextDouble() * 100;
        }

        Arrays.sort(z, Collections.reverseOrder());

        System.out.println(Arrays.toString(z));
    }
}