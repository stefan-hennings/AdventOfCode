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
        System.out.println(expenses);
        System.out.println(findTwoFactors());
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

    public int findTwoFactors() {
        while (true) {
            if (expenses.get(0) + expenses.get(expenses.size()-1) > 2020) {
                expenses.remove(expenses.size()-1);
            } else if (expenses.get(0) + expenses.get(expenses.size()-1) < 2020) {
                expenses.remove(0);
            } else {
                return expenses.get(0) * expenses.get(expenses.size()-1);
            }
        }
    }

    public static void main(String[] args) {
        new Puzzle1();
    }
}
