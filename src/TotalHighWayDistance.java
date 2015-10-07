import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by baidu on 15/9/29.
 */
public class TotalHighWayDistance {
    public static void main(String[] args) {
        java.util.Scanner s = new java.util.Scanner(
//                System.in
                new ByteArrayInputStream(("3 5\n" +
                        "1 2 2\n" +
                        "2 3 3\n" +
                        "QUERY\n" +
                        "EDIT 1 2 4\n" +
                        "QUERY\n" +
                        "EDIT 2 3 2\n" +
                        "QUERY").getBytes())
        );
        int n = s.nextInt();
        int m = s.nextInt();
        HashMap<Integer, HashMap<Integer, Integer>> distanceMap = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            int from = s.nextInt();
            int to = s.nextInt();
            int distance = s.nextInt();
            HashMap<Integer, Integer> map1 = distanceMap.get(from);
            if (map1 == null) {
                map1 = new HashMap<>();
                distanceMap.put(from, map1);
            }
            map1.put(to, distance);
            HashMap<Integer, Integer> map2 = distanceMap.get(to);
            if (map2 == null) {
                map2 = new HashMap<>();
                distanceMap.put(to, map2);
            }
            map2.put(from, distance);
        }
        HashMap<Integer, HashMap<Integer, Integer>> distanceMapCopy = new HashMap<>(distanceMap);
        for (int i = 0; i < m; i++) {
            s.useDelimiter("\\n");
            String op = s.next();
            if (op.startsWith("EDIT")) {
                distanceMapCopy = new HashMap<>(distanceMap);
                String[] data = op.split(" ");
                int from = Integer.valueOf(data[1]);
                int to = Integer.valueOf(data[2]);
                int distance = Integer.valueOf((data[3]));
                distanceMapCopy.get(from).put(to, distance);
                distanceMapCopy.get(to).put(from, distance);
            } else {
                System.out.println(findThd(distanceMapCopy, n));
            }
        }
    }
    public static int findThd(HashMap<Integer, HashMap<Integer, Integer>> distanceMap, int n) {
        int thd = 0;
        ArrayList<int[]> pendingRequest = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
//                HashMap<Integer, Integer> start = distanceMap.get(i + 1);
                Integer d = distanceMap.get(i + 1).get(j + 1);
                if (d == null) {
                    boolean found = false;
                    for (int k = 0; k < n && k != i && k != j; k++) {
                        Integer router1, router2;
                        router1 = distanceMap.get(i + 1).get(k + 1);
                        router2 = distanceMap.get(k + 1).get(j + 1);
                        if (router1 != null && router2 != null) {
                            thd += router1 + router2;
                            found = true;
                            distanceMap.get(i+1).put(j+1, router1 + router2);
                            distanceMap.get(j+1).put(i+1, router1 + router2);
                            break;
                        }
                    }
                    if (!found) {
                        pendingRequest.add(new int[]{i, j});
                    }
                } else {
                    thd += d;
                }
            }
        }
        while (pendingRequest.size() > 0) {
            int[] request = pendingRequest.get(0);
            int i = request[0], j = request[1];
            boolean found = false;
            for (int k = 0; k < n && k != i && k != j; k++) {
                Integer router1, router2;
                router1 = distanceMap.get(i + 1).get(k + 1);
                router2 = distanceMap.get(k + 1).get(j + 1);
                if (router1 != null && router2 != null) {
                    thd += router1 + router2;
                    found = true;
                    distanceMap.get(i+1).put(j+1, router1 + router2);
                    distanceMap.get(j+1).put(i+1, router1 + router2);
                    break;
                }
            }
            pendingRequest.remove(0);
            if (!found) {
                pendingRequest.add(pendingRequest.size(), request);
            }
        }
        return thd;
    }
}
