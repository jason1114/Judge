import java.io.ByteArrayInputStream;
import java.util.Scanner;

/**
 * Created by baidu on 15/9/23.
 */
public class WaterPump {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(
//                    System.in
                new ByteArrayInputStream(("5\n" +
                        "2 3.2 4 4.5 6").getBytes())
        );
        int pumpNum = scanner.nextInt();
        float[] radiusArray = new float[pumpNum];
        for (int i = 0; i < pumpNum; i++) {
            float radius = scanner.nextFloat();
            radiusArray[i] = radius;
        }

    }
}
