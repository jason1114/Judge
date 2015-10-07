import java.util.*;

/**
 * Created by baidu on 15/9/7.
 */
public class BrowserCaching {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int historySize = scanner.nextInt();
        int cacheSize = scanner.nextInt();
        LinkedList<String> history = new LinkedList<>();
        for (int i = 0; i < historySize; i++) {
            String url = scanner.next();
            history.add(url);
        }
        new BrowserCaching().getResult(cacheSize, historySize, history);
    }

    public void getResult(int cacheSize, int historySize, LinkedList<String> history) {
        LinkedList<String> cache = new LinkedList<>();
        for (String url: history) {
            int index = cache.indexOf(url);
            if (index != -1) {
                cache.remove(index);
                System.out.println("Cache");
            } else if (cache.size() >= cacheSize) {
                cache.remove(cacheSize - 1);
                System.out.println("Internet");
            } else {
                System.out.println("Internet");
            }
            cache.add(0, url);
        }
    }
}
