package Day10to17.puzzle15;

import utility.ExecutionTime;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Puzzle15 {
    String test = "PuzzleInputs\\test";
    String test2 = "PuzzleInputs\\test2";
    String real = "PuzzleInputs\\Day10to17\\puzzle15";

    int[] history;
    String[] stringArray;

    public Puzzle15() {
        findTheNthNumber(2020);
        findTheNthNumber(30000000);
    }

    private void findTheNthNumber(int n) {
        history = new int[n];
        initializeData();

        int previousNumber = Integer.parseInt(stringArray[stringArray.length-1]);
        int currentNumber = 0;
        for (int i = stringArray.length + 1; i <= n; i++) {
            if (history[previousNumber] == 0) {
                //say 0
                //It was the first time, so store last turn in its index
                history[previousNumber] = i - 1;
                previousNumber = 0;
            } else {
                currentNumber = i - 1 - history[previousNumber];
                history[previousNumber] = i - 1;
                previousNumber = currentNumber;
            }
        }
        System.out.println(currentNumber);
    }

    private void initializeData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(real))){
            stringArray = reader.readLine().split(",");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < stringArray.length - 1; i++) {
            history[Integer.parseInt(stringArray[i])] = i + 1;
        }
    }

    public static void main(String[] args) {
        ExecutionTime.start();
        new Puzzle15();
        ExecutionTime.stop();
    }
}
