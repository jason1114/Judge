import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class FlowerGrowth {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(
                System.in
//                new ByteArrayInputStream(("5\n" +
//                        "1 1 1 1 2 2 3 2 2 1 1 1").getBytes())
        );
        int height = scanner.nextInt();
        int[] rule = new int[12];
        for (int i = 0; i < 12; i++) {
            rule[i] = scanner.nextInt();
        }
        for (int i = 1; i <= 12; i++) {
            for (int j = 0; j < 12 - i + 1; j++) {
                int sum = 0;
                for (int k = 0; k < i; k++) {
                    sum += rule[j + k];
                }
                if (sum >= height) {
                    System.out.println(i);
                    return;
                }
            }
        }
        System.out.println(-1);
    }
}
