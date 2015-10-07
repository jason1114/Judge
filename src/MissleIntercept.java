import java.io.ByteArrayInputStream;
import java.util.Scanner;

/**
 * Created by baidu on 15/9/28.
 */
public class MissleIntercept {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(
//                System.in
                new ByteArrayInputStream(("2\n" +
                        "8\n" +
                        "389 207 155 300 299 170 158 65\n" +
                        "3\n" +
                        "88 34 65").getBytes())
        );
        int testNum = scanner.nextInt();
        for (int i = 0; i < testNum; i ++) {
            int missleNum = scanner.nextInt();
            int[] missleArray = new int[missleNum];
            for (int j = 0; j < missleNum; j++) {
                missleArray[j] = scanner.nextInt();
            }
            System.out.println(getMaxInterceptNum(Integer.MAX_VALUE, missleArray, 0));
        }
    }
    public static int getMaxInterceptNum(int biggest, int[] array, int startIndex) {
        if (startIndex == array.length - 1) {
            if (array[startIndex] <= biggest) {
                return 1;
            } else {
                return 0;
            }
        } else {
            if (array[startIndex] <= biggest) {
                int solutionOne = getMaxInterceptNum(array[startIndex], array, startIndex+1 )+1;
                int solutionTwo = getMaxInterceptNum(biggest, array, startIndex+1);
                return Math.max(solutionOne, solutionTwo);
            } else {
                return getMaxInterceptNum(biggest, array, startIndex+1 );
            }
        }
    }
}
