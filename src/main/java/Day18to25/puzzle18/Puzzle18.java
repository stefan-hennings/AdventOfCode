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
            expression = calculateParenthesisPart1(expression);
            result += calculateExpressionPart1(expression);
        }
        System.out.println("Part 1: " + result);
    }
    
    private String calculateParenthesisPart1(String expression) {
        while (expression.contains("(")) {
            int parenthesisStart = expression.lastIndexOf("(");
            int parenthesisEnd = expression.indexOf(")", parenthesisStart);
            String parenthesis = expression.substring(parenthesisStart + 1, parenthesisEnd);
            String result = Long.toString(calculateExpressionPart1(parenthesis));
//            expression = expression.replace("(" + parenthesis + ")", result);
            expression = expression.substring(0, parenthesisStart) + result + expression.substring(parenthesisEnd + 1);
    
        }
        return expression;
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
        long result = 0;
        for (String expression : allExpressions) {
            expression = calculateParenthesisPart2(expression);
            result += calculateExpressionPart2(expression);
        }
        System.out.println("Part 2: " + result);
    }
    
    private String calculateParenthesisPart2(String expression) {
        while (expression.contains("(")) {
            int parenthesisStart = expression.lastIndexOf("(");
            int parenthesisEnd = expression.indexOf(")", parenthesisStart);
            String parenthesis = expression.substring(parenthesisStart + 1, parenthesisEnd);
            String result = Long.toString(calculateExpressionPart2(parenthesis));
//            expression = expression.replace("(" + parenthesis + ")", result);
            expression = expression.substring(0, parenthesisStart) + result + expression.substring(parenthesisEnd + 1);
        }
        return expression;
    }
    
    private long calculateExpressionPart2(String expression) {
        expression = calculateAdditionPart2(expression);
        String[] multiplicationArray = expression.split(" ");
        long result = Integer.parseInt(multiplicationArray[0]);
        for (int i = 1; i < multiplicationArray.length - 1; i += 2) {
            long secondNumber = Long.parseLong(multiplicationArray[i + 1]);
            result *= secondNumber;
        }
        return result;
    }
    
    private String calculateAdditionPart2(String expression) {
        while (expression.contains("+")) {
            int plusSignIndex = expression.indexOf("+");
            int additionStart = expression.lastIndexOf(" ", plusSignIndex - 2) + 1;
            int additionEnd = expression.indexOf(" ", plusSignIndex + 2) - 1;
            if (additionEnd == -2) {
                additionEnd = expression.length() - 1;
            }
            String addition = expression.substring(additionStart, additionEnd + 1);
            String[] additionArray = addition.split(" ");
            long result = Long.parseLong(additionArray[0]) + Long.parseLong(additionArray[2]);
//            expression = expression.replace(addition, Long.toString(result)); This turns 7 + 7 + 8 * 77 + 7 into 14 + 8 * 714
            expression = expression.substring(0, additionStart) + result + expression.substring(additionEnd + 1);
        }
        return expression;
    }
    
    public static void main(String[] args) {
        ExecutionTime.start();
        new Puzzle18();
        ExecutionTime.stop();
    }
}
