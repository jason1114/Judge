import java.io.ByteArrayInputStream;
import java.util.*;

/**
 * Created by baidu on 15/9/21.
 */
public class FindDeadLock {
    public static void main(String[] args) {
        Map<String, Lock> lockMap = new HashMap<>();
        Map<String, Process> processMap = new HashMap<>();
        Scanner scanner = new Scanner(
//                System.in
                new ByteArrayInputStream(("7\n" +
                        "123\t1001,1002\t1003\n" +
                        "127\t1100\t \n" +
                        "128\t1010\t \n" +
                        "129\t \t \n" +
                        "124\t1007\t1008\n" +
                        "125\t1003\t1004\n" +
                        "126\t1004\t1002").getBytes())
        ).useDelimiter("\\n");
        int totalLineNumbers = scanner.nextInt();
        for (int i = 0; i < totalLineNumbers; i++) {
            String raw = scanner.next();
            String[] info = raw.split("\t");
            String processId = info[0];
            String occupyLocks = info[1];
            String waitLocks = info[2];
            Process p = new Process();
            if (occupyLocks.trim().length() > 0) {
                String[] result = occupyLocks.split(",");
                for (int j = 0; j < result.length; j++) {
                    Lock lock = lockMap.get(result[j]);
                    if (lock == null) {
                        lock = new Lock();
                        lockMap.put(result[j], lock);
                    }
                    lock.occupyBy.add(p);
                    p.occupy.add(lock);
                }
            }
            if (waitLocks.trim().length() > 0) {
                String[] result = waitLocks.split(",");
                for (int j = 0; j < result.length; j++) {
                    Lock lock = lockMap.get(result[j]);
                    if (lock == null) {
                        lock = new Lock();
                        lockMap.put(result[j], lock);
                    }
                    lock.waitBy.add(p);
                    p.wait.add(lock);
                }
            }
            processMap.put(processId, p);
        }

    }
    static class Lock {
        List<Process> occupyBy = new LinkedList<>();
        List<Process> waitBy = new LinkedList<>();
    }
    static class Process {
        List<Lock> occupy = new LinkedList<>();
        List<Lock> wait = new LinkedList<>();
    }
}
