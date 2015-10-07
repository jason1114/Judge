/**
 * Created by baidu on 15/9/29.
 */
public class Cakes {
    public static void main(String[] args) {

    }

    public static int getMethodNum(int rowNum, int colNum, int[] lastRow) {
        if (rowNum == 1) {
            int sum = 0;
            for (int i = 0; i < colNum; i++) {
                if (lastRow[i] == 1) {
                    sum ++;
                }
            }
            if (colNum == 3) {
                if (sum == 1) {
                    return 1;
                } else {
                    return 0;
                }
            } else if (colNum == 4) {
                if (sum == 2 && !(lastRow[1] == 1 && lastRow[2] == 1)) {
                    return 1;
                } else {
                    return 0;
                }
            } else if (colNum == 5) {
                if (sum == 2 || sum == 4) {
                    return 0;
                } else if (sum == 1) {
                    if (lastRow[1] == 1 || lastRow[3] == 1) {
                        return 0;
                    } else {
                        return 1;
                    }
                } else if (sum == 3) {
                    if ((lastRow[3] == 0 && lastRow[4] == 0) || (lastRow[2] == 0 && lastRow[3] == 0)
                            || (lastRow[1] == 0 && lastRow[2] == 0) || (lastRow[0] == 0 && lastRow[1] == 0)) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            }
        } else {
            return 0;
        }
        return 0;
    }
}
