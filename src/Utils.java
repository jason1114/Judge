/**
 * Created by baidu on 15/10/5.
 */
public class Utils {

    /**
     * 矩阵复制
     * @param matrix
     * @return
     */
    public static int[][] matrixCopy(int[][] matrix) {
        if (matrix != null) {
            int[][] newMatrix = new int[matrix.length][matrix[0].length];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    newMatrix[i][j] = matrix[i][j];
                }
            }
            return newMatrix;
        } else {
            return null;
        }
    }
}
