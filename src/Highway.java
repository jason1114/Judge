import java.io.ByteArrayInputStream;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Created by baidu on 15/9/28.
 */
public class Highway {
    public static void main(String[] args) {
        java.util.Scanner s = new java.util.Scanner(
//                System.in
                new ByteArrayInputStream(("3\n" +
                        "3 5 1\n" +
                        "1 4 2\n" +
                        "0 8 3").getBytes())
        );
        int carNumber = s.nextInt();
        LinkedList<Car> cars = new LinkedList<>();
        LinkedList<Car> carsCopy = new LinkedList<>();
        for (int i = 0; i < carNumber; i++) {
            int x = s.nextInt();
            int y = s.nextInt();
            int l = s.nextInt();
            Car car = new Car(x, y, l);
            cars.add(car);
            carsCopy.add(car);
        }

        cars.sort(new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                return o1.x - o2.x;
            }
        });
        for (int i = 0; i < carNumber; i ++) {
            Car car = cars.get(i);
            boolean lastSlowerThanMe, nextFasterThanMe;
            if (i == 0 || (cars.get(i-1).l <= car.l)) {
                lastSlowerThanMe = true;
            } else {
                lastSlowerThanMe = false;
            }
            if (i == carNumber - 1 || (car.l <= cars.get(i+1).l)) {
                nextFasterThanMe = true;
            } else {
                nextFasterThanMe = false;
            }
            if (lastSlowerThanMe && nextFasterThanMe) {
                // ok
                double value = (double)(car.y - car.x)/car.l;
                car.time = (double)Math.round(value * 100d) / 100d;
            } else if (lastSlowerThanMe && !nextFasterThanMe) {
                Car nextCar = cars.get(i+1);
                double timeTookToMeet = (double)(nextCar.l - car.l)/(nextCar.x - car.x);
                double distanceRunWhenMeet = timeTookToMeet*car.l;
                if (distanceRunWhenMeet < car.y - car.x) {
                    // 追上时当前车还没驶离高速
                    double restTime = (double)(car.y - car.x - distanceRunWhenMeet)/nextCar.l;
                    double value = timeTookToMeet + restTime;
                    car.time = (double)Math.round(value * 100d) / 100d;
                } else {
                    // 在追上前就已经驶离高速
                    double value = (double)(car.y - car.x)/car.l;
                    car.time = (double)Math.round(value * 100d) / 100d;
                }
            } else if (!lastSlowerThanMe && nextFasterThanMe) {
                // ok
                double value = (double)(car.y - car.x)/car.l;
                car.time = (double)Math.round(value * 100d) / 100d;
            } else if (!lastSlowerThanMe && !nextFasterThanMe) {

            }

        }
    }
    static class Car {
        int x;
        int y;
        int l;
        double time;

        public Car(int x, int y, int l) {
            this.x = x;
            this.y = y;
            this.l = l;
        }

        @Override
        public String toString() {
            return "("+x+","+ y+","+l+")";
        }
    }
}
