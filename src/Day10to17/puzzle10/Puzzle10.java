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
    long totalPossiblePaths = 1;

    public Puzzle10() {
        adapters = Utility.readIntegerFile(real);
        adapters.add(0); //Add the wall
        Collections.sort(adapters);
        adapters.add(adapters.get(adapters.size() - 1) + 3); //Add the computer

        part1();

        part2(); //Relies on the fact that there is *never* a difference of 2 between the original adapters without another one in between
    }

    private void part1() {
        int[] differenceTracker = new int[3];
        for (int i = 1; i < adapters.size(); i++) {
            differenceTracker[adapters.get(i) - adapters.get(i - 1) - 1]++;
        }
        System.out.printf("Differences: %s, result: %d%n", Arrays.toString(differenceTracker), differenceTracker[0] * differenceTracker[2]);
    }

    private void part2() {
        for (int blockStart = 0; blockStart < adapters.size() - 1; blockStart++) {
            if (adapters.get(blockStart + 1) - adapters.get(blockStart) < 3) {
                blockStart = findBlockEnd(blockStart);
            }
        }
        System.out.println(totalPossiblePaths);
    }

    private int findBlockEnd(int blockStart) {
        for (int blockEnd = blockStart + 1; blockEnd < adapters.size() - 1; blockEnd++) {
            if (adapters.get(blockEnd + 1) - adapters.get(blockEnd) == 3) {
                totalPossiblePaths *= findBlockPaths(blockStart, blockEnd);
                return blockEnd;
            }
        }
        throw new IllegalArgumentException("There is no block end");
    }

    private int findBlockPaths(int startIndex, int endIndex) {
        int arrangementsInPath = 1;
        for (int i = endIndex - startIndex - 1; i > 0; i--) {
            arrangementsInPath += i;
        }
        return arrangementsInPath;
    }

    public static void main(String[] args) {
        ExecutionTime.start();
        new Puzzle10();
        ExecutionTime.stop();
    }
}
