package puzzle7;

import puzzle6.Puzzle6;

public class Puzzle7 {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        new Puzzle6();
        System.out.printf("Execution time: %f milliseconds%n", ((double) System.nanoTime() - startTime) / 1000000);
    }
}
