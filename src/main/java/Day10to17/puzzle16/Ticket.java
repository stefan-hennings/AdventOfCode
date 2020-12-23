package Day10to17.puzzle16;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Ticket {
    int[] values;
    
    public Ticket(String input) {
        String[] inputArray = input.split(",");
        values = new int[inputArray.length];
        for (int i = 0; i < inputArray.length; i++) {
            values[i] = Integer.parseInt(inputArray[i]);
        }
    }
}
