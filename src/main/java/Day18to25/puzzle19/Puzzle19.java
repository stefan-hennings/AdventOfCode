package Day18to25.puzzle19;

import lombok.Getter;
import utility.ExecutionTime;
import utility.Utility;

import java.util.ArrayList;
import java.util.List;

public class Puzzle19 {
    String test = "test";
    String real = "Day18to25\\puzzle19";
    
    @Getter
    private static List<Rule> ruleList = new ArrayList<>();
    private List<String> messages = new ArrayList<>();
    
    public Puzzle19() {
        initializeData();
        
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
    }
    
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
