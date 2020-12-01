package puzzle1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Puzzle1 {
    List<Integer> expenses = new ArrayList<>();

    public Puzzle1() {
        readFile();
        Collections.sort(expenses);
        findTwoFactors(2020);
    }

    public void readFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src\\puzzle1\\puzzle1input.txt"))) {
            String nextLine;
            while ((nextLine = reader.readLine()) != null) {
                expenses.add(Integer.parseInt(nextLine));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void findTwoFactors(int targetValue) {
        int smallIndex = 0;
        int bigIndex = expenses.size()-1;
        while (true) {
            if (expenses.get(smallIndex) + expenses.get(bigIndex) > targetValue) {
                bigIndex--;
            } else if (expenses.get(smallIndex) + expenses.get(bigIndex) < targetValue) {
                smallIndex++;
            } else {
                System.out.printf("Found %d and %d. Final product is %d.\n",
                        expenses.get(smallIndex), expenses.get(bigIndex),
                        expenses.get(smallIndex) * expenses.get(bigIndex));
                break;
            }
        }
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        new Puzzle1();
        System.out.println(System.nanoTime() - startTime);
    }
}
