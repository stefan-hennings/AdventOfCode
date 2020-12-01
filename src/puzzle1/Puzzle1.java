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
        findTwoFactors();
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

    public void findTwoFactors() {
        int smallIndex = 0;
        int bigIndex = expenses.size()-1;
        while (true) {
            if (expenses.get(smallIndex) + expenses.get(bigIndex) > 2020) {
                bigIndex--;
            } else if (expenses.get(smallIndex) + expenses.get(bigIndex) < 2020) {
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
        new Puzzle1();
    }
}
