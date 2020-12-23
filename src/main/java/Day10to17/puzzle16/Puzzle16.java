package Day10to17.puzzle16;

import utility.ExecutionTime;
import utility.Utility;

import java.util.ArrayList;
import java.util.List;

public class Puzzle16 {
    String test = "test";
    String real = "Day10to17\\puzzle16";
    
    List<Rule> ruleList = new ArrayList<>();
    Ticket myTicket;
    List<Ticket> otherTickets = new ArrayList<>();

    public Puzzle16() {
        initializeData();
    
        part1();

        part2();
    }
    

    private void initializeData() {
        List<String> input = Utility.readStringFile(test);
        final int READING_RULES = 0;
        final int READING_MY_TICKET = 1;
        final int READING_OTHER_TICKETS = 2;
        
        int state = READING_RULES;
        for (String line : input) {
            if (line.isEmpty()) {
                state++;
                continue;
            } else if (line.contains("ticket")) {
                continue;
            }
            switch (state) {
                case READING_RULES -> ruleList.add(new Rule(line));
                case READING_MY_TICKET -> myTicket = new Ticket(line);
                case READING_OTHER_TICKETS -> otherTickets.add(new Ticket(line));
            }
        }
    }
    
    private void part1() {

    }

    private void part2() {

    }

    public static void main(String[] args) {
        ExecutionTime.start();
        new Puzzle16();
        ExecutionTime.stop();
    }
}
