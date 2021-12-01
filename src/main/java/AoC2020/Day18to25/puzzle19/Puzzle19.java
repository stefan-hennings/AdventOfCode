package AoC2020.Day18to25.puzzle19;

import lombok.Getter;
import utility.ExecutionTime;
import utility.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Puzzle19 {
    String test = "test";
    String real = "AoC2020.Day18to25\\puzzle19";
    
    @Getter
    private static List<Rule> ruleList = new ArrayList<>();
    private List<String> messages = new ArrayList<>();
    
    public Puzzle19() {
        initializeData();
        ruleList.get(0).extractDigits();
        String rule0 = ruleList.get(0).getCondition()
                .replaceAll("(\"a\")", "a")
                .replaceAll("(\"b\")", "b")
                .replaceAll("\\(a\\) \\(a\\)", "aa")
                .replaceAll("\\(b\\) \\(b\\)", "bb")
                .replaceAll("\\(a\\) \\(b\\)", "ab")
                .replaceAll("\\(b\\) \\(a\\)", "ba")
                ;
        System.out.println("rule0 = " + rule0);
        StringBuilder stringBuilder = new StringBuilder(rule0.trim());
        int counter = 0;
        for (int i = 0; i < stringBuilder.length(); i++) {
            if (counter == 0 && stringBuilder.charAt(i) == ' ') {
                stringBuilder.setCharAt(i,'>');
            } else if (counter != 1 && stringBuilder.charAt(i) == ' ') {
                stringBuilder.delete(i, i + 1);
            }
            if (stringBuilder.charAt(i) == '(') {
                counter++;
            } else if (stringBuilder.charAt(i) == ')') {
                counter--;
            }
        }
    
        System.out.println("stringBuilder = " + stringBuilder);
//        ruleList.forEach(System.out::println);
        part1();
        
        part2();
    }
    
    private void initializeData() {
        List<String> input = Utility.readStringFile(test);
        boolean readingRules = true;
        for (String line : input) {
            if (line.isEmpty()) {
                readingRules = false;
                continue;
            }
            if (readingRules) {
                ruleList.add(new Rule(line));
            } else {
                messages.add(line);
            }
        }
        Collections.sort(ruleList);
    }
    
//    private void extractAllRulesToZero() {
//        Rule ruleZero =
//        while (ruleList.get())
//    }
    
    private void part1() {
    
    }
    
    private void part2() {
    
    }
    
    public static void main(String[] args) {
        ExecutionTime.start();
        new Puzzle19();
        ExecutionTime.stop();
    }
}
