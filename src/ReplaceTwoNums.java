import java.io.ByteArrayInputStream;
import java.util.Scanner;

/**
 * Created by baidu on 15/9/21.
 */
public class ReplaceTwoNums {
    public static void main(String[] args) {
        Scanner scan = new Scanner(
//                    System.in
                new ByteArrayInputStream(("4 2 1 3 4\n" +
                        "5 5 4 3 2 1\n" +
                        "0").getBytes())
        );
        int num = scan.nextInt();
        while (num != 0) {
            int[] array = new int[num];
            int max = 0, index = 0;
            for (int i = 0; i < num; i++) {
                int n = scan.nextInt();
                array[i] = n;
                if (n > max) {
                    max = n;
                    index = i;
                }
            }
            array[index] = array[0];
            array[0] = max;
            num = scan.nextInt();
            for (int i = 0; i < array.length; i++) {
                System.out.print(array[i]);
                if (i != array.length -1 ) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
