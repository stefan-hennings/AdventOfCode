package puzzle6;

import utility.Utility;

import java.util.List;

public class Puzzle6 {
    List<String> allGroups = Utility.readStringFile("puzzle6");
    int[] answers = new int[26];
    public Puzzle6() {
        allGroups.add("");
        part1();
        part2();
    }

    private void part1() {
        int sum = 0;
        for (String line : allGroups) {
            if (!line.equals("")) {
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

    private void part2() {
        int sum = 0;
        int groupMembers = 0;
        for (String line : allGroups) {
            if (!line.equals("")) {
                groupMembers++;
                for (int i = 0; i < line.length(); i++) {
                    answers[line.charAt(i) - 97]++; //increment array index based on ASCII value
                }
            } else {
                for (int i = 0; i < answers.length; i++) {
                    if (answers[i] == groupMembers) {
                        sum++;
                    }
                    answers[i] = 0;
                }
                groupMembers = 0;
            }
        }
        System.out.println(sum);
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        new Puzzle6();
        System.out.printf("Execution time: %f milliseconds%n", ((double) System.nanoTime() - startTime) / 1000000);
    }
}
