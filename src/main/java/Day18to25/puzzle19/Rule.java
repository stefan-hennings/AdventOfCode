package Day18to25.puzzle19;

import lombok.Getter;

@Getter
public class Rule implements Comparable<Rule> {
    private int ruleId;
    private String condition;
    
    public Rule(String input) {
        int colonIndex = input.indexOf(':');
        ruleId = Integer.parseInt(input.substring(0, colonIndex));
        condition = input.substring(colonIndex + 2);
    }
    
    @Override
    public int compareTo(Rule o) {
        return ruleId - o.getRuleId();
    }
}
