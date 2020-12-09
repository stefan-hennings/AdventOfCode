package puzzle9;

import utility.ExecutionTime;
import utility.Utility;

import java.util.Collections;
import java.util.List;

public class Puzzle9 {
    List<Long> allNumbers;
    int preamble;
    long encryptionWeakness;

    public Puzzle9() {
        boolean testing = false;
        String filename = testing ? "test" : "puzzle9";
        preamble = testing ? 5 : 25;
        allNumbers = Utility.readLongFile(filename);

        for (int i = preamble; i < allNumbers.size(); i++) {
            if (!isValidNumber(allNumbers.get(i), i)) {
                System.out.printf("Number %d in index %d is not valid%n", allNumbers.get(i), i);
                return;
            }
        }
    }

    private boolean isValidNumber(long number, int index) {
        System.out.println("Checking " + number);
        System.out.println("index = " + index);
        List<Long> subList = allNumbers.subList(index - preamble, index);
        System.out.println(subList);
        for (int i = 0; i < subList.size() - 1; i++) {
            for (int j = i + 1; j < subList.size(); j++) {
                if (subList.get(i) + subList.get(j) == number) {
                    System.out.printf("%d + %d = %d%n", subList.get(i), subList.get(j), number);
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ExecutionTime.start();
        new Puzzle9();
        ExecutionTime.stop();
    }
}
