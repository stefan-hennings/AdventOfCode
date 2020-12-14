package Day10to17.puzzle13;

import utility.ExecutionTime;
import utility.Utility;

import java.util.Arrays;
import java.util.List;

public class Puzzle13 {
    String test = "test";
    String real = "Day10to17\\puzzle13";

    List<String> allBusses;

    public Puzzle13() {
        List<String> strings = Utility.readStringFile(real);
        allBusses = Arrays.asList(strings.get(1).split(",").clone());
        part1(Integer.parseInt(strings.get(0)));

//        busCycleTime();
    }

    private void busCycleTime() {
        long product = 1;
        for (String bus : allBusses) {
            try {
                product *= Integer.parseInt(bus);
            } catch (NumberFormatException ignored) {
            }
        }
        System.out.println("product = " + product);
        for (String bus : allBusses) {
            int busID;
            try {
                busID = Integer.parseInt(bus);
                System.out.printf("Bus %d: %d%n", busID, product%busID);
            } catch (NumberFormatException ignored) {
            }
        }
    }

    private void part1(long startTime) {
        long waitTime = Integer.MAX_VALUE;
        int earliestBus = 0;
        for (String bus : allBusses) {
            int busID;
            try {
                busID = Integer.parseInt(bus);
            } catch (NumberFormatException e) {
                continue;
            }
            long timeToDeparture = (startTime % busID - busID) * -1;
            if (timeToDeparture < waitTime) {
                waitTime = timeToDeparture;
                earliestBus = busID;
            }
        }
        System.out.printf("Bus %d leaves in %d minutes. Result: %d%n", earliestBus, waitTime, earliestBus*waitTime);
    }

    public static void main(String[] args) {
        ExecutionTime.start();
        new Puzzle13();
        ExecutionTime.stop();
    }
}
