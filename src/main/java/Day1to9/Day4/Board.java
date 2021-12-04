package Day1to9.Day4;

import kotlin.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Board {
    private int[][] grid = new int[5][5];
    private Map<Integer, Pair<Integer, Integer>> positionLookup = new HashMap<>();
    private Set<Integer> pickedNumbers = new HashSet<>();
    private int numbersToWin = Integer.MAX_VALUE;
    private int winningNumber = 0;
    
    
    public Board(String gridString) {
        String[] lines = gridString.split(System.lineSeparator());
        for (int row = 0; row < lines.length; row++) {
            String[] numbers = lines[row].trim().replaceAll(" {2}", " ").split(" ");
            for (int column = 0; column < numbers.length; column++) {
                int currentNumber = Integer.parseInt(numbers[column]);
                grid[row][column] = currentNumber;
                positionLookup.put(currentNumber, new Pair<>(row, column));
            }
        }
    }
    
    public int countNumbersNeededToWin(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            int currentNumber = numbers[i];
            Pair<Integer, Integer> position = positionLookup.get(currentNumber);
            if (position != null) {
                pickedNumbers.add(currentNumber);
                if (checkWin(position.component1(), position.component2())) {
                    numbersToWin = i + 1;
                    winningNumber = currentNumber;
                    break;
                }
            }
        }
        
        
        return numbersToWin;
    }
    
    private boolean checkWin(int row, int column) {
        return isHorizontalWin(row) || isVerticalWin(column);
    }
    
    private boolean isHorizontalWin(int row) {
        for (int column = 0; column < 5; column++) {
            if (!pickedNumbers.contains(grid[row][column])) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isVerticalWin(int column) {
        for (int row = 0; row < 5; row++) {
            if (!pickedNumbers.contains(grid[row][column])) {
                return false;
            }
        }
        return true;
    }
    
    private int getSumOfUnusedNumbers() {
        int sum = 0;
        for (int[] row : grid) {
            for (int number : row) {
                if (!pickedNumbers.contains(number)) {
                    sum += number;
                }
            }
        }
        return sum;
    }
    
    public int getWinningScore() {
        return getSumOfUnusedNumbers() * winningNumber;
    }
    
    @Override
    public String toString() {
        return "Board{numbersToWin=" + numbersToWin +
                ", winningNumber=" + winningNumber +
                '}';
    }
    
    public int getNumbersToWin() {
        return numbersToWin;
    }
}
