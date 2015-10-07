import java.io.ByteArrayInputStream;

/**
 * Created by baidu on 15/9/29.
 */
public class FarthestPoint {
    public static void main(String[] args) {
        java.util.Scanner s = new java.util.Scanner(
                System.in
//                new ByteArrayInputStream(("1.000 1.000 1.500").getBytes())
        );
        float x = s.nextFloat();
        float y = s.nextFloat();
        float r = s.nextFloat();
        float startX = x - r;
        float endX = x + r;

        double maxDistance = 0;
        int maxX = 0;
        int maxY = 0;
        boolean integralFount = false;
        for (int testX = floor(startX); testX <= floor(endX); testX++) {
            float testYOffset = (float) Math.sqrt(Math.pow(r, 2) - Math.pow(x - testX, 2));
            int testY1 = floor(y + testYOffset);
            int testY2 = floor(y - testYOffset);
            if (y - testYOffset == testY2) {
                maxX = testX;
                maxY = testY2;
                maxDistance = r;
                integralFount = true;
            }
            if (y + testYOffset == testY1) {
                maxX = testX;
                maxY = testY1;
                maxDistance = r;
                integralFount = true;
            }
            if (integralFount) {
                continue;
            }
            double xDistance = Math.pow(testX - x, 2);
            double test1 = xDistance + Math.pow(testY1 - y, 2);
            double test2 = xDistance + Math.pow(testY2 - y, 2);
            if (test2 >= maxDistance) {
                maxDistance = test2;
                maxX = testX;
                maxY = testY1;
            }
            if (test1 >= maxDistance) {
                maxDistance = test1;
                maxX = testX;
                maxY = testY1;
            }
        }
        System.out.println(maxX + " " + maxY);
    }
    public static int floor(float num) {
        if (num > 0) {
            return (int) Math.floor(num);
        } else {
            return (int) -Math.floor(-num);
        }
    }
}
