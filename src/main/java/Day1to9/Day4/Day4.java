package Day1to9.Day4;

import utility.ExecutionTime;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day4 {
    String test = "PuzzleInputs/test";
    String real = "src/main/java/Day1to9/Day4/input.txt";
    
    String input;
    int[] drawnNumbers;
    Board[] boards;
    
    {
        try {
            input = Files.readString(Paths.get(real));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Day4() {
        ExecutionTime.start();
    
        prepData();
    
        System.out.println(part1());
        
        prepData();
        
        System.out.println(part2());
        
        ExecutionTime.stop();
    }
    
    private void prepData() {
        String[] split = input.split("\\r\\n\\r\\n");
        String[] stringNumbers = split[0].split(",");
        System.out.println();
        drawnNumbers = new int[stringNumbers.length];
        for (int i = 0; i < stringNumbers.length; i++) {
            drawnNumbers[i] = Integer.parseInt(stringNumbers[i]);
        }
        
        List<Board> boardList = new ArrayList<>();
        for (int i = 1; i < split.length; i++) {
            Board board = new Board(split[i]);
            boardList.add(board);
        }
        boards = boardList.toArray(new Board[0]);
    }
    
    public int part1() {
        int previousFastestWin = Integer.MAX_VALUE;
        Board leadingBoard = boards[0];
        for (Board currentBoard : boards) {
            int numbersToWinCurrentBoard = currentBoard.countNumbersNeededToWin(drawnNumbers);
            if (numbersToWinCurrentBoard < previousFastestWin) {
                previousFastestWin = numbersToWinCurrentBoard;
                leadingBoard = currentBoard;
            }
        }
        return leadingBoard.getWinningScore();
    }
    
    public int part2() {
        int previousSlowestWin = Integer.MIN_VALUE;
        Board leadingBoard = boards[0];
        int counter = 1;
        int lastBoard = 0;
        for (Board currentBoard : boards) {
            int numbersToWinCurrentBoard = currentBoard.countNumbersNeededToWin(drawnNumbers);
            counter++;
            if (numbersToWinCurrentBoard > previousSlowestWin) {
                previousSlowestWin = numbersToWinCurrentBoard;
                leadingBoard = currentBoard;
                lastBoard = counter;
            }
        }
        System.out.println(leadingBoard);
        System.out.println("last board " + lastBoard);
        return leadingBoard.getWinningScore();
    }
    
    public static void main(String[] args) {
        new Day4();
    }
}
