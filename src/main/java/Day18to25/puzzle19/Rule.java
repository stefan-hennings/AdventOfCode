package Day18to25.puzzle19;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Rule implements Comparable<Rule> {
    private int ruleId;
    private String condition;
    
    public Rule(String input) {
        int colonIndex = input.indexOf(':');
        ruleId = Integer.parseInt(input.substring(0, colonIndex));
        condition = input.substring(colonIndex + 1) + " ";
    }
    
    public String extractDigits() {
        if (condition.matches(".*[0-9].*")) { //contains digits
            String[] split = condition.split(" \\| ");
            for (String option : split) {
                String[] ruleArray = option.trim().split(" ");
                for (String ruleId : ruleArray) {
                    String replacement = Puzzle19.getRuleList().get(Integer.parseInt(ruleId)).extractDigits();
                    System.out.printf("Condition: *%s*, ruleId: *%s*, replacement: *%s* %n", condition, ruleId, replacement);
                    condition = condition.replace(ruleId, replacement);
                    System.out.println("condition: *" + condition + "*\n");
                }
            }
        }
        return "(" + condition.trim() + ")";
    }
    
    @Override
    public int compareTo(Rule o) {
        return ruleId - o.getRuleId();
    }
}
