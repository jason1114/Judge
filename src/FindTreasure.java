import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by baidu on 15/9/23.
 */
public class FindTreasure {
    static int[][] matrix;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(
//                    System.in
                new ByteArrayInputStream(("2 2 2\n" +
                        "1 2\n" +
                        "2 1").getBytes())
        );
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int k = scanner.nextInt();
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                System.out.print(matrix[i][j]+" ");
//            }
//            System.out.println();
//        }
        System.out.println(getMethodNum(n-1, m-1, k, 13));
    }

    public static long getMethodNum (int xEnd, int yEnd, int k, int smallerThan) {
        if (k >= smallerThan) {
            return 0;
        }
        if (k > xEnd + yEnd + 1) {
            return 0;
        }
        if (k == 0) {
            if (xEnd > 0 && yEnd > 0) {
                return jiecheng(xEnd, yEnd);
            } else {
                // 只能横着走或者竖着走或者在可走区域只剩原点
                return 1;
            }
        }
        // k >= 1
        if (xEnd == 0 && yEnd == 0) {
            if (k == 1 && matrix[0][0] < smallerThan) {
                return 1;
            } else {
                return 0;
            }
        }
        // r1 最后一步是往下走的， r2 最后一步是往右走的， 总方法数是r1 + r2
        long r1, r2;
        if (matrix[xEnd][yEnd] >= smallerThan) {
            // 最后一格格子中的数不能拿
            r1 = xEnd > 0 ? getMethodNum(xEnd - 1, yEnd, k, smallerThan) : 0;
            r2 = yEnd > 0 ? getMethodNum(xEnd, yEnd-1, k, smallerThan) : 0;
        } else {
            // 拿最后一格的数字 + 不拿最后一格的数字
            r1 = xEnd > 0 ? (getMethodNum(xEnd -1, yEnd, k-1, matrix[xEnd][yEnd]) +
                    getMethodNum(xEnd - 1, yEnd, k, smallerThan)) : 0;
            r2 = yEnd > 0 ? getMethodNum(xEnd, yEnd-1, k-1, matrix[xEnd][yEnd]) +
                    getMethodNum(xEnd, yEnd-1, k, smallerThan) : 0;
        }
        return r1 + r2;
    }
    public static long jiecheng(int m, int n) {
        int bigger, smaller;
        if (m > n) {
            bigger = m;
            smaller = n;
        } else {
            bigger = n;
            smaller = m;
        }
        int t = m + n;
        long fenzi = 1;
        for (int i = bigger + 1; i<=t; i++) {
            fenzi *= i;
        }
        long fenmu = 1;
        for (int i=1; i<= smaller; i++) {
            fenmu *= i;
        }
        return fenzi/fenmu;
    }
}
