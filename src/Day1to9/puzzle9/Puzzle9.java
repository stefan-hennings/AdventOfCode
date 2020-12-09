package Day1to9.puzzle9;

import utility.ExecutionTime;
import utility.Utility;

import java.util.List;

public class Puzzle9 {
    List<Long> allNumbers;
    int preamble;
    long invalidNumber;

    public Puzzle9() {
        boolean testing = false;
        String filename = testing ? "test" : "Day1to9\\puzzle9";
        preamble = testing ? 5 : 25;
        allNumbers = Utility.readLongFile(filename);

        part1();
        part2();
    }

    private void part1() {
        for (int i = preamble; i < allNumbers.size(); i++) {
            if (!isValidNumber(allNumbers.get(i), i)) {
                invalidNumber = allNumbers.get(i);
                System.out.printf("Number %d in index %d is not valid%n", invalidNumber, i);
                return;
            }
        }
    }

    private boolean isValidNumber(long number, int index) {
        List<Long> subList = allNumbers.subList(index - preamble, index);
        for (int i = 0; i < subList.size() - 1; i++) {
            for (int j = i + 1; j < subList.size(); j++) {
                if (subList.get(i) + subList.get(j) == number) {
                    return true;
                }
            }
        }
        return false;
    }

    private void part2() {
        long sum;
        long smallest;
        long biggest;
        for (int i = 0; i < allNumbers.size() - 1; i++) {
            smallest = allNumbers.get(i);
            biggest = smallest;
            sum = smallest;
            long jValue;
            for (int j = i + 1; sum <= invalidNumber; j++) {
                jValue = allNumbers.get(j);
                sum += jValue;
                if (jValue < smallest) {
                    smallest = jValue;
                } else if (jValue > biggest) {
                    biggest = jValue;
                }
                if (sum == invalidNumber) {
                    System.out.printf("The encryption weakness is: %d%n", smallest + biggest);
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        ExecutionTime.start();
        new Puzzle9();
        ExecutionTime.stop();
    }
}
