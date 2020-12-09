package Day1to9.puzzle1;

import utility.ExecutionTime;
import utility.Utility;

import java.util.Collections;
import java.util.List;

public class Puzzle1 {
    List<Integer> expenses = Utility.readIntegerFile("Day1to9\\puzzle1");

    public Puzzle1() {
        Collections.sort(expenses);
        int[] twoFactors = findTwoFactors(2020);
        System.out.printf("Found %d and %d. Final product is %d.%n", twoFactors[0], twoFactors[1], twoFactors[0] * twoFactors[1]);

        int[] threeFactors = findThreeFactors(2020);
        System.out.printf("Found %d, %d and %d. Final product is %d%n", threeFactors[0], threeFactors[1], threeFactors[2],
                threeFactors[0] * threeFactors[1] * threeFactors[2]);

        int[] threeFactorsNew = findThreeFactorsAlternate(2020);
        System.out.printf("Found %d, %d and %d. Final product is %d%n", threeFactorsNew[0], threeFactorsNew[1], threeFactorsNew[2],
                threeFactorsNew[0] * threeFactorsNew[1] * threeFactorsNew[2]);
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

    public int[] findThreeFactorsAlternate(int targetValue) {
        int smallIndex = 0;
        int bigIndex = expenses.size() - 1;
        int sum;
        while (true) {
            sum = expenses.get(smallIndex) + expenses.get(bigIndex);
            if (sum > targetValue) {
                bigIndex--;
            } else if (expenses.contains(targetValue-sum)) {
                return new int[]{targetValue-sum, expenses.get(smallIndex), expenses.get(bigIndex)};
            } else if (smallIndex >= bigIndex){
                smallIndex++;
                bigIndex = expenses.size()-1;
            } else {
                bigIndex--;
            }
        }
    }

    public static void main(String[] args) {
        ExecutionTime.start();
        new Puzzle1();
        ExecutionTime.stop();
    }
}
