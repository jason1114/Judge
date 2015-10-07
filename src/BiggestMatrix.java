import java.io.ByteArrayInputStream;

/**
 * Created by baidu on 15/9/25.
 */
public class BiggestMatrix {
    public static void main(String[]  args){
        java.util.Scanner s = new java.util.Scanner(
                System.in
//                new ByteArrayInputStream(("1 2 0 3 4 ; " +
//                        "2 3 4 5 1 ; " +
//                        "1 1 5 3 0").getBytes())
        ).useDelimiter("\\A");
        String input =  s.hasNext() ? s.next() : "";

        int[][] matrix = null;
        String[] rowList = input.split(";");
        int m = 0,n = rowList.length;
        if (n < 2) {
            System.out.println(0);
            return;
        }
        for (int r = 0; r < rowList.length; r++) {
            String[] numList = rowList[r].split(" ");
            if (matrix == null) {
                matrix = new int[rowList.length][numList.length];
                m = numList.length;
                if (m < 2) {
                    System.out.println(0);
                    return;
                }
            }
            for (int c = 0; c < numList.length; c++) {
                matrix[r][c] = Integer.valueOf(numList[c].trim());
            }
        }

        int biggest = 0;
        for (int row = 0; row <= n - 2; row ++) {
            for (int col = 0; col <= m - 2; col ++) {
                int sum = matrix[row][col] + matrix[row][col+1] + matrix[row + 1][col] + matrix[row+1][col + 1];
                if (sum > biggest) {
                    biggest = sum;
                }
            }
        }
        System.out.println(biggest);
    }
}

//    public static void printMatrix(int[][] matrix, int startRow, int startCol, int endRow, int endCol) {
//        for (int i = startRow; i <= endRow; i++) {
//            for (int j = startCol; j <= endCol; j++) {
//                System.out.print(matrix[i][j]);
//                if (j == endCol) {
//                    System.out.println();
//                } else {
//                    System.out.print(" ");
//                }
//            }
//        }
//    }
