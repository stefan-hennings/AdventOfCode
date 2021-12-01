package AoC2020.Day10to17.puzzle16;

import lombok.Getter;
import utility.ExecutionTime;
import utility.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class Puzzle16 {
    String test = "test";
    String real = "AoC2020.Day10to17\\puzzle16";
    
    private List<Rule> ruleList = new ArrayList<>();
    private Ticket myTicket;
    private List<Ticket> otherTickets = new ArrayList<>();

    public Puzzle16() {
        initializeData();
    
        part1();

        part2();
    }
    

    private void initializeData() {
        List<String> input = Utility.readStringFile(real);
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
        int errorRate = 0;
        for (Ticket ticket : otherTickets) {
            for (int value : ticket.getValues()) {
                if (!isValidValue(value)) {
                    errorRate += value;
                    ticket.setInvalid(); //For part 2
                }
            }
        }
        System.out.println("errorRate = " + errorRate);
    }
    
    private boolean isValidValue(int value) {
        for (Rule rule : ruleList) {
            if (rule.isInRange(value)) {
                return true;
            }
        }
        return false;
    }
    
    private void part2() {
        otherTickets.removeIf(ticket -> !ticket.isValid());
        for (Rule rule : ruleList) {
            for (int position = 0; position < myTicket.getValues().length; position++) {
                if (isValidRuleForPosition(rule, position)) {
                    rule.addPossiblePosition(position);
                }
            }
        }
        Collections.sort(ruleList);
        for (int i = 0; i < ruleList.size(); i++) {
            if (ruleList.get(i).getPossiblePositions().size() != 1) {
                throw new IllegalStateException("Possible positions not equal to 1 for rule " + ruleList.get(i));
            }
            removePositionFromRules(ruleList.get(i).getPossiblePositions().get(0), i + 1);
        }
        long product = 1;
        for (Rule rule : ruleList) {
            if (rule.getType().startsWith("departure")) {
                product *= myTicket.getValues()[rule.getPossiblePositions().get(0)];
            }
        }
        System.out.println("product = " + product);
    }
    
    private boolean isValidRuleForPosition(Rule rule, int position) {
        for (Ticket ticket : otherTickets) {
            if (!rule.isInRange(ticket.getValues()[position])) {
                return false;
            }
        }
        return true;
    }
    
    private void removePositionFromRules(int position, int fromRule) {
        for (int i = fromRule; i < ruleList.size(); i++) {
            ruleList.get(i).removePossiblePosition(position);
        }
    }
    
    public static void main(String[] args) {
        ExecutionTime.start();
        new Puzzle16();
        ExecutionTime.stop();
    }
}
