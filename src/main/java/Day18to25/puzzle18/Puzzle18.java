package Day18to25.puzzle18;

import utility.ExecutionTime;
import utility.Utility;

import java.util.List;

public class Puzzle18 {
    String test = "test";
    String real = "Day18to25\\puzzle18";
    
    List<String> allExpressions;
    
    public Puzzle18() {
        allExpressions = Utility.readStringFile(real);
        
        part1();
        
        part2();
    }
    
    private void part1() {
        long result = 0;
        for (String expression : allExpressions) {
            while (expression.contains("(")) {
                int parenthesisStart = expression.lastIndexOf("(");
                int parenthesisEnd = expression.indexOf(")", parenthesisStart);
                String parenthesis = expression.substring(parenthesisStart + 1, parenthesisEnd);
                expression = expression.replace("(" + parenthesis + ")", calculateParenthesis(parenthesis));
            }
            long temp = calculateExpressionPart1(expression);
            System.out.println("temp = " + temp);
            result += temp;
        }
        System.out.println("Part 1: " + result);
    }
    
    private String calculateParenthesis(String expression) {
        long result = calculateExpressionPart1(expression);
        return Long.toString(result);
    }
    
    private long calculateExpressionPart1(String expression) {
        String[] expressionArray = expression.split(" ");
        long result = Integer.parseInt(expressionArray[0]);
        for (int i = 1; i < expressionArray.length - 1; i += 2) {
            int secondNumber = Integer.parseInt(expressionArray[i + 1]);
            result = switch (expressionArray[i]) {
                case "+" -> result + secondNumber;
                case "*" -> result * secondNumber;
                default -> throw new IllegalStateException("Unexpected operator: " + expressionArray[1]);
            };
        }
        return result;
    }
    
    private void part2() {
    
    }
    
    public static void main(String[] args) {
        ExecutionTime.start();
        new Puzzle18();
        ExecutionTime.stop();
    }
}
