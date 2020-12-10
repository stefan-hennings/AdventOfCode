package Day10to17.puzzle10;

import utility.ExecutionTime;
import utility.Utility;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Puzzle10 {
    String test1 = "test";
    String test2 = "test2";
    String real = "Day10to17\\puzzle10";

    List<Integer> adapters;

    int goalJolt;

    public Puzzle10() {
        adapters = Utility.readIntegerFile(test2);
        adapters.add(0);
        Collections.sort(adapters);

        part1();

        part2();
    }

    private void part1() {
        int[] differenceTracker = new int[3];
        for (int i = 1; i < adapters.size(); i++) {
            differenceTracker[adapters.get(i) - adapters.get(i - 1) - 1]++;
        }
        goalJolt = adapters.get(adapters.size() - 1) + 3;
        differenceTracker[2]++;
        System.out.printf("Differences: %s, result: %d%n", Arrays.toString(differenceTracker), differenceTracker[0] * differenceTracker[2]);
        System.out.println(goalJolt);
    }

    private void part2() {
        long arrangements = 1;
        for (int i = 2; i < adapters.size(); i++) {
            if (adapters.get(i) - adapters.get(i - 2) <= 3) {
                arrangements *= 2;
                System.out.println("Found one at " + adapters.get(i));
                if (i >= 3 && adapters.get(i) - adapters.get(i - 3) <= 3) {
//                    arrangements *= 2;
                    System.out.println("And another!");
                }
                System.out.println(arrangements);
            }
        }
        System.out.println(arrangements);
    }


    public static void main(String[] args) {
        ExecutionTime.start();
        new Puzzle10();
        ExecutionTime.stop();
    }
}
