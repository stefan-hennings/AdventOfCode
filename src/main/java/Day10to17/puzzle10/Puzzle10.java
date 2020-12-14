package Day10to17.puzzle10;

import utility.ExecutionTime;
import utility.Utility;

import java.util.*;

public class Puzzle10 {
    String test1 = "test";
    String test2 = "test2";
    String real = "Day10to17\\puzzle10";

    List<Integer> adapters;
    long totalPossiblePaths = 1;

    public Puzzle10() {
        adapters = Utility.readIntegerFile(real);
        adapters.add(0); //Add the wall
        Collections.sort(adapters);
        adapters.add(adapters.get(adapters.size() - 1) + 3); //Add the computer

        part1();

        part2();
    }

    private void part1() {
        int[] differenceTracker = new int[3];
        for (int i = 1; i < adapters.size(); i++) {
            differenceTracker[adapters.get(i) - adapters.get(i - 1) - 1]++;
        }
        System.out.printf("Differences: %s, result: %d%n", Arrays.toString(differenceTracker), differenceTracker[0] * differenceTracker[2]);
    }

    private void part2() {
        Map<Integer, Long> possiblePaths = new HashMap<>();
        possiblePaths.put(-3, 1L);
        for (int voltage : adapters) {
            possiblePaths.put(voltage,
                    possiblePaths.getOrDefault(voltage-1, 0L) +
                    possiblePaths.getOrDefault(voltage-2, 0L) +
                    possiblePaths.getOrDefault(voltage-3, 0L)
            );
        }
        System.out.println(possiblePaths.get(adapters.get(adapters.size() - 1)));
    }

    public static void main(String[] args) {
        ExecutionTime.start();
        new Puzzle10();
        ExecutionTime.stop();
    }
}
