package Day10to17.puzzle16;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Rule {
    private String type;
    private List<int[]> validRanges = new ArrayList<>();
    
    
    public Rule(String input) {
        int colonIndex = input.indexOf(':');
        this.type = input.substring(0, colonIndex);
        
        String[] validRanges = input.substring(colonIndex + 1).split(" or ");
        for (String range : validRanges) {
            int dashIndex = range.indexOf('-');
            this.validRanges.add(new int[]{
                    Integer.parseInt(range.substring(0, dashIndex)), Integer.parseInt(range.substring(dashIndex + 1))
            });
        }
    }
}
