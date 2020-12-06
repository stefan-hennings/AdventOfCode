package puzzle6;

import utility.Utility;

import java.util.List;

public class Puzzle6 {
    List<String> allGroups = Utility.readStringFile("puzzle6");
    int[] answers = new int[26];

    public Puzzle6() {
        int sum = 0;
        allGroups.add("");
        for (String line : allGroups) {
            if (!line.equals("")) {
                System.out.println("processing line " + line);
                for (int i = 0; i < line.length(); i++) {
                    answers[line.charAt(i) - 97]++; //increment array index based on ASCII value
                }
            } else {
                for (int i = 0; i < answers.length; i++) {
                    if (answers[i] > 0) {
                        sum++;
                        answers[i] = 0;
                    }
                }
            }
        }
        System.out.println(sum);
    }

    public static void main(String[] args) {
        new Puzzle6();
    }
}