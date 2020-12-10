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

    public Puzzle10() {
        adapters = Utility.readIntegerFile(real);
        adapters.add(0); //Add the wall
        Collections.sort(adapters);
        adapters.add(adapters.get(adapters.size() - 1) + 3); //Add the computer

        part1();

//        adapters.forEach(System.out::println);

//        part2();
    }

    private void part1() {
        int[] differenceTracker = new int[3];
        for (int i = 1; i < adapters.size(); i++) {
            differenceTracker[adapters.get(i) - adapters.get(i - 1) - 1]++;
        }
        System.out.printf("Differences: %s, result: %d%n", Arrays.toString(differenceTracker), differenceTracker[0] * differenceTracker[2]);
    }

    private void part2() {
        long arrangements = 1;

        /*for (int i = 2; i < adapters.size(); i++) {
            if (adapters.get(i) - adapters.get(i - 2) <= 3) {
                System.out.println("Found one at " + adapters.get(i));
//                if (i >= 3 && adapters.get(i) - adapters.get(i - 3) <= 3) {
//                    arrangements *= 4;
//                    System.out.println("And another!");
//                } else {
//                }
                arrangements *= 2;
                System.out.println(arrangements);
            }
        }*/
        for (int blockStart = 1; blockStart < adapters.size(); blockStart++) {
            if (adapters.get(blockStart + 1) - adapters.get(blockStart) < 3) {
                System.out.println("blockStart = " + blockStart);
                arrangements = findBlockEnd(arrangements, blockStart);
            }
        }
        System.out.println(arrangements);
    }

    private long findBlockEnd(long arrangements, int blockStart) {
        for (int blockEnd = blockStart + 1; blockEnd < adapters.size(); blockEnd++) {
            if (adapters.get(blockEnd + 1) - adapters.get(blockEnd) == 3) {
                System.out.println("blockEnd = " + blockEnd);
                arrangements *= findBlockPaths(blockStart, blockEnd);
            }
        }
        return arrangements;
    }

    private int findBlockPaths(int startIndex, int endIndex) {
        return 1;
    }

    public static void main(String[] args) {
        ExecutionTime.start();
        new Puzzle10();
        ExecutionTime.stop();
    }
}
