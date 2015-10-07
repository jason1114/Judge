import java.io.ByteArrayInputStream;
import java.util.*;

/**
 * Created by baidu on 15/9/8.
 */
public class MemoryAllocatingAlgorithm {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(
//                System.in
                new ByteArrayInputStream(("6 10\n" +
                        "5 2 2 2 4 4").getBytes())
        );
        int inputNum = scanner.nextInt();
        int memorySize = scanner.nextInt();
        int[] inputSizeArray = new int[inputNum];
        for (int i = 0; i < inputNum; i++) {
            inputSizeArray[i] = scanner.nextInt();
        }
//        System.out.println("inputNum:" + inputNum);
//        System.out.println("memorySize:" + memorySize);
//        System.out.println("inputSizeArray:" + Arrays.toString(inputSizeArray));
        int[] memory = new int[memorySize];
        for (int i = 0; i < inputNum; i++) {
            int value = i+1;
            int dataSize = inputSizeArray[i];
            while (!findSpaceAndStore(memory, dataSize, value)) {
                allocate(memory);
            }
        }
        printMemory(memory);
    }

    public static void printMemory (int[] memory) {
        List<Result> list = new LinkedList<>();
        for (int i = 0; i < memory.length; i++) {
            if (memory[i] != 0 && (i == 0 || memory[i-1] != memory[i])) {
                list.add(new Result(memory[i], i));
            }
        }
        list.sort(new Comparator<Result>() {
            @Override
            public int compare(Result o1, Result o2) {
                return o1.id - o2.id;
            }
        });
        for (Result r : list) {
            System.out.println(r.id + " " + r.pos);
        }
    }
    static class Result {
        int id;
        int pos;

        public Result(int id, int pos) {
            this.id = id;
            this.pos = pos;
        }
    }

    public static boolean findSpaceAndStore(int[] memory, int size, int value) {
        int spaceBeginIndex = -1;
        int spaceSize = 0;
        for (int i = 0; i < memory.length; i++) {
            if (memory[i] == 0) {
                if (spaceBeginIndex != -1) {
                    spaceSize++;
                } else {
                    spaceBeginIndex = i;
                    spaceSize++;
                }
                if (spaceSize >= size) {
                    break;
                }
            } else {
                spaceBeginIndex = -1;
                spaceSize = 0;
            }
        }
        if (spaceBeginIndex >= 0 && spaceSize >= size) {
            for (int i = spaceBeginIndex; i < spaceBeginIndex + size; i++) {
                memory[i] = value;
            }
            return true;
        } else {
            return false;
        }
    }

    public static void allocate(int[] memory) {
        int smallest = memory[0];
        int smallestIndex = 0;
        for (int i = 1; i < memory.length; i++) {
            if (memory[i] != 0 && memory[i] < smallest) {
                smallest = memory[i];
                smallestIndex = i;
            }
        }
        int i = smallestIndex;
        while (i < memory.length && memory[i] == smallest) {
            memory[i] = 0;
            i++;
        }
    }
}