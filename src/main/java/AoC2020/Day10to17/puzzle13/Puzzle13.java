package AoC2020.Day10to17.puzzle13;

import utility.ExecutionTime;
import utility.Utility;

import java.util.Arrays;
import java.util.List;

public class Puzzle13 {
    String test = "test";
    String real = "AoC2020.Day10to17\\puzzle13";

    List<String> allBusses;

    public Puzzle13() {
        List<String> strings = Utility.readStringFile(real);
        allBusses = Arrays.asList(strings.get(1).split(",").clone());
        part1(Integer.parseInt(strings.get(0)));

        part2();
    }

    private void part1(long startTime) {
        long waitTime = Integer.MAX_VALUE;
        int earliestBus = 0;
        for (String bus : allBusses) {
            int busId;
            try {
                busId = Integer.parseInt(bus);
            } catch (NumberFormatException e) {
                continue;
            }
            long timeToDeparture = (startTime % busId - busId) * -1;
            if (timeToDeparture < waitTime) {
                waitTime = timeToDeparture;
                earliestBus = busId;
            }
        }
        System.out.printf("Bus %d leaves in %d minutes. Result: %d %n%n", earliestBus, waitTime, earliestBus*waitTime);
    }

    private void part2() {
        long previousBussesProduct = 1L;
        long earliestSyncedTime = 0;
        for (int offset = 0; offset < allBusses.size(); offset++) {
            if (allBusses.get(offset).equals("x")) {
                continue;
            }
            int busId = Integer.parseInt(allBusses.get(offset));
            while ((earliestSyncedTime + offset) % busId != 0) {
                earliestSyncedTime += previousBussesProduct;
            }
            previousBussesProduct *= busId;
        }
        System.out.printf("The busses start leaving at %,d. %n", earliestSyncedTime);
    }

    public static void main(String[] args) {
        ExecutionTime.start();
        new Puzzle13();
        ExecutionTime.stop();
    }
}
