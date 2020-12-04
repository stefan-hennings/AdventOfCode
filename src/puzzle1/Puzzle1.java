package puzzle1;

import utility.Utility;
import java.util.Collections;
import java.util.List;

public class Puzzle1 {
    List<Integer> expenses;

    public Puzzle1() {
        expenses = Utility.readIntegerFile("puzzle1");
        Collections.sort(expenses);
        int[] twoFactors = findTwoFactors(2020);
        System.out.printf("Found %d and %d. Final product is %d.%n", twoFactors[0], twoFactors[1], twoFactors[0] * twoFactors[1]);
        int[] threeFactors = findThreeFactors(2020);
        System.out.printf("Found %d, %d and %d. Final product is %d%n", threeFactors[0], threeFactors[1], threeFactors[2],
                threeFactors[0] * threeFactors[1] * threeFactors[2]);
    }

    public int[] findTwoFactors(int targetValue) {
        int smallIndex = 0;
        int bigIndex = expenses.size() - 1;
        int sum;
        for (int i = 1; i < expenses.size(); i++) {
            sum = expenses.get(smallIndex) + expenses.get(bigIndex);
            if (sum > targetValue) {
                bigIndex--;
            } else if (sum < targetValue) {
                smallIndex++;
            } else {
                return new int[]{expenses.get(smallIndex), expenses.get(bigIndex)};
            }
        }
        return null;
    }

    public int[] findThreeFactors(int targetValue) {
        for (Integer currentInt : expenses) {
            int[] twoFactors = findTwoFactors(targetValue - currentInt);
            if (twoFactors != null && twoFactors[0] != currentInt && twoFactors[1] != currentInt) {
                return new int[]{currentInt, twoFactors[0], twoFactors[1]};
            }
        }
        return null;
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        new Puzzle1();
        System.out.printf("Execution time: %f milliseconds%n", ((double) System.nanoTime() - startTime) / 1000000);
        System.out.println();
    }
}
