import java.io.ByteArrayInputStream;
import java.util.Scanner;

/**
 * Created by baidu on 15/9/28.
 */
public class GetSortNum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(
                System.in
//                new ByteArrayInputStream(("3\n" +
//                        "abcdefghijkl\n" +
//                        "hgebkflacdji\n" +
//                        "gfkedhjblcia").getBytes())
        );
        int testNum = scanner.nextInt();
        for (int i = 0; i < testNum; i++) {
            String line = scanner.nextLine();
            while (line == null || line.length() == 0) {
                line = scanner.nextLine();
            }
            System.out.println(
                    perm2num(12,
                            characterArray2IntArray(line.toCharArray())
                    ) + 1
            );
        }
    }
    static int[] characterArray2IntArray (char[] characterArray) {
        int[] result = new int[characterArray.length];
        for (int i = 0; i < characterArray.length; i++) {
            result[i] = characterArray[i] - 'a' + 1;
        }
        return result;
    }

    static int perm2num (int n, int[] array){
        int i, j, num = 0,k = 1;
        for (i = n-2;i >= 0;k *= n-(i--)) {
            for (j = i + 1; j < n; j++) {
                if (array[j] < array[i]) {
                    num += k;
                }
            }
        }
        return num;
    }
}
