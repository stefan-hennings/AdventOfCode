package utility;

public class ExecutionTime {
    static long startTime;

    public static void start() {
        startTime = System.nanoTime();
    }

    public static void stop() {
        System.out.printf("%nExecution time: %f milliseconds%n", ((double) System.nanoTime() - startTime) / 1000000);
    }
}
