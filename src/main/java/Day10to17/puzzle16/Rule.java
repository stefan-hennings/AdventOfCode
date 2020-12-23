package Day10to17.puzzle16;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class Rule {
    private String type;
    private List<int[]> validRanges = new ArrayList<>();
    
    
    public Rule(String input) {
        int colonIndex = input.indexOf(':');
        this.type = input.substring(0, colonIndex);
        
        String[] validRanges = input.substring(colonIndex + 2).split(" or ");
        for (String range : validRanges) {
            int dashIndex = range.indexOf('-');
            final int lowLimit = Integer.parseInt(range.substring(0, dashIndex));
            final int highLimit = Integer.parseInt(range.substring(dashIndex + 1));
            this.validRanges.add(new int[]{lowLimit, highLimit});
        }
    }
}
