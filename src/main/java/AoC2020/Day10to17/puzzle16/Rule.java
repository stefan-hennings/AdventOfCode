package AoC2020.Day10to17.puzzle16;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class Rule implements Comparable<Rule> {
    private String type;
    private List<int[]> validRanges = new ArrayList<>();
    private List<Integer> possiblePositions = new ArrayList<>();
    
    public Rule(String input) {
        int colonIndex = input.indexOf(':');
        this.type = input.substring(0, colonIndex);
        
        String[] validRanges = input.substring(colonIndex + 2).split(" or ");
        for (String range : validRanges) {
            int dashIndex = range.indexOf('-');
            int lowLimit = Integer.parseInt(range.substring(0, dashIndex));
            int highLimit = Integer.parseInt(range.substring(dashIndex + 1));
            this.validRanges.add(new int[]{lowLimit, highLimit});
        }
    }
    
    public boolean isInRange(int value) {
        for (int[] range : validRanges) {
            if (value >= range[0] && value <= range[1]) {
                return true;
            }
        }
        return false;
    }
    
    public void addPossiblePosition(int position) {
        possiblePositions.add(position);
    }
    
    public void removePossiblePosition(int position) {
        possiblePositions.removeIf(integer -> integer == position);
    }
    
    @Override
    public int compareTo(Rule o) {
        return possiblePositions.size() - o.getPossiblePositions().size();
    }
}
