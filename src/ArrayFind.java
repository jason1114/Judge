import java.io.ByteArrayInputStream;

/**
 * AC
 * Created by baidu on 15/9/25.
 */
public class ArrayFind {
    public static void main(String[] args) {
        java.util.Scanner s = new java.util.Scanner(
                    System.in
//                new ByteArrayInputStream(("1 2 3 4 -1 -2 -4 -3 1 2").getBytes())
        ).useDelimiter("\\A");
        String input =  s.hasNext() ? s.next() : "";
        String[] array = null;
        if (input != null && input.length() > 0) {
            array = input.split(" ");
            for (int testLength = array.length; testLength > 0 ;testLength--) {
                for (int offset = 0; offset <= array.length - testLength; offset++) {
                    int sum = 0;
                    for (int i = offset; i < testLength + offset; i++) {
                        sum += Integer.valueOf(array[i].trim());
                    }
                    if (sum == 0) {
                        for (int i = offset; i < testLength + offset; i++) {
                            System.out.print(array[i] + " ");
                        }
                        return;
                    }
                }
            }
        }
    }
}
