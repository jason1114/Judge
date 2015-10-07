import java.io.ByteArrayInputStream;
import java.util.*;

/**
 *
 * TLE
 * http://hihocoder.com/contest/hiho66/problem/1
 * Created by baidu on 15/10/5.
 */
public class HaveLunchTogether {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(
                System.in
//                new ByteArrayInputStream(("10 10\n" +
//                        "##########\n" +
//                        "#...P##..#\n" +
//                        "#S#...#.P#\n" +
//                        "#S#..#...#\n" +
//                        "#...#.####\n" +
//                        "#.#...#.H#\n" +
//                        "##......##\n" +
//                        "#..P#..S.#\n" +
//                        "##.......#\n" +
//                        "##########").getBytes())
        );
        String dimension = scanner.nextLine();
        String[] dimensions = dimension.split(" ");
        int n = Integer.valueOf(dimensions[0]);
        int m = Integer.valueOf(dimensions[1]);
        Block[][] map = new Block[n][m];
        Point startPoint = null;
        LinkedList<Point> seats = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            String row = scanner.nextLine();
            char[] cells = row.toCharArray();
            for (int j = 0; j < m; j++) {
                map[i][j] = new Block(cells[j]);
                if (cells[j] == Block.START_POINT) {
                    startPoint = new Point(i, j);
                } else if (cells[j] == Block.OCCUPIED_BY_SEAT) {
                    seats.add(new Point(i, j));
                }
            }
        }

        // BFS Search for adjacentSeats

        /**
         * 已经到达的座位以及到达该座位最短长度
         */
        HashMap<Point, Integer> reachedSeats = new HashMap<>();
        /**
         * 相邻的空位子
         */
        LinkedList<Point[]> adjacentSeats = new LinkedList<>();
        /**
         * 出发点
         */
        Path startPath = new Path(new int[n][m], startPoint);
        /**
         * BFS 需要借助的队列
         */
        Queue<Path> pathQueue = new LinkedList<>();
        /**
         * 加入 root 启动 BFS
         */
        pathQueue.offer(startPath);
        do {
            Queue<Path> newPathQueue = new LinkedList<>();
            Path path;
            while ((path = pathQueue.poll()) != null) {
                Point current = path.current;
                Point top, right, bottom, left;
                if (current.row > 0) {
                    // 当前位置上方尚未超出矩阵
                    top = new Point(current.row - 1, current.col);
                    if (testPoint(reachedSeats, seats, map, newPathQueue, path, top, adjacentSeats)) {
                        break;
                    }
                }
                if (current.col < map[0].length - 1) {
                    right = new Point(current.row, current.col + 1);
                    if (testPoint(reachedSeats, seats, map, newPathQueue, path, right, adjacentSeats)) {
                        break;
                    }

                }
                if (current.row < map.length - 1) {
                    bottom = new Point(current.row + 1, current.col);
                    if (testPoint(reachedSeats, seats, map, newPathQueue, path, bottom, adjacentSeats)) {
                        break;
                    }
                }
                if (current.col > 0) {
                    left = new Point(current.row, current.col - 1);
                    if (testPoint(reachedSeats, seats, map, newPathQueue, path, left, adjacentSeats)) {
                        break;
                    }
                }
            }
            pathQueue = newPathQueue;
        } while (pathQueue.size() > 0);

        // 无路可走退出或者所有座位已经遍历完毕
        int minLength = Integer.MAX_VALUE;
        for (Point[] adjacent: adjacentSeats) {
            int testLength = reachedSeats.get(adjacent[0]) + reachedSeats.get(adjacent[1]);
            if (testLength < minLength) {
                minLength = testLength;
            }
        }
        if (minLength == Integer.MAX_VALUE) {
            System.out.println("Hi and Ho will not have lunch.");
        } else {
            System.out.println(minLength);
        }
    }

    /**
     *
     * @param reachedSeats
     * @param seats
     * @param map
     * @param newQueue
     * @param path
     * @param point
     * @return true 所有座位都已经到达，可以退出外层循环了
     */
    private static boolean testPoint(HashMap<Point, Integer> reachedSeats, LinkedList<Point> seats,
                                     Block[][] map, Queue<Path> newQueue, Path path, Point point, LinkedList<Point[]> adjacentPoints) {
        if (seats.contains(point) && !reachedSeats.containsKey(point)) {
            // 要去的座位包含这个位置，且已经到达的位子不包括这个位置
            Point seatB = point;
            for (Point seatA: reachedSeats.keySet()) {
                if ((seatA.row == seatB.row && Math.abs(seatA.col - seatB.col) == 1)
                        || (seatA.col == seatB.col && Math.abs(seatA.row - seatB.row) == 1)) {
                    // 如果这时候找到两个相邻的座位
                    adjacentPoints.add(new Point[] {seatA, seatB});
                }
            }
            reachedSeats.put(point, path.length + 1);
            if (reachedSeats.size() == seats.size()) {
                // 已经到达所有座位
                return true;
            }
        } else if (map[point.row][point.col].getStatus() == Block.EMPTY && !path.hasWalked(point)) {
            // 该位置是个空位
            newQueue.offer(path.move(point));
        }
        return false;
    }

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

    static class Point {
        int row, col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Point) {
                if (((Point) obj).row == row && ((Point) obj).col == col) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return (row + "," + col).hashCode();
        }
    }

    static class Path {
        Point current;
        int [][] path;
        int length = -1;

        public Path(int[][] path, Point startPoint) {
            this.path = path;
            setStart(startPoint.row, startPoint.col);
        }

        public Path(int[][] path) {
            this.path = path;
        }

        public void setStart(int row, int col) {
            if (current == null) {
                current = new Point(row, col);
            } else {
                current.row = row;
                current.col = col;
            }
            path[row][col] = 1;
            length ++;
        }

        public Path move(Point p) {
            Path newPath = new Path(matrixCopy(path));
            newPath.current = p;
            newPath.length = length + 1;
            newPath.path[p.row][p.col] = 1;
            return newPath;
        }

        public boolean hasWalked(Point p) {
            return path[p.row][p.col] == 1;
        }
    }

    static class Block {
        static final char
                OCCUPIED_BY_PEOPLE = 'P',
                OCCUPIED_BY_SEAT = 'S',
                OCCUPIED_BY_OBSTRUCTION = '#',
                EMPTY = '.',
                START_POINT = 'H';

        char status;

        public Block(char status) {
            this.status = status;
        }

        public char getStatus() {
            return status;
        }

        public void setStatus(char status) {
            this.status = status;
        }
    }
}