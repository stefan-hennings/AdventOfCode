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
        System.out.println(part2());
//        bothParts();
        
        ExecutionTime.stop();
    }
    
    private void prepData() {
        String[] split = input.split("\\r\\n\\r\\n");
        String[] stringNumbers = split[0].split(",");
        drawnNumbers = new int[stringNumbers.length];
        for (int i = 0; i < stringNumbers.length; i++) {
            drawnNumbers[i] = Integer.parseInt(stringNumbers[i]);
        }
        
        List<Board> boardList = new ArrayList<>();
        for (int i = 1; i < split.length; i++) {
            Board board = new Board(split[i]);
            board.countNumbersNeededToWin(drawnNumbers);
            boardList.add(board);
        }
        boards = boardList.toArray(new Board[0]);
    }
    
    public int part1() {
        Board fastestBoard = boards[0];
        int previousFewestDraws = fastestBoard.getDrawsToWin();
        for (Board currentBoard : boards) {
            int drawsToWinCurrentBoard = currentBoard.getDrawsToWin();
            if (drawsToWinCurrentBoard < previousFewestDraws) {
                previousFewestDraws = drawsToWinCurrentBoard;
                fastestBoard = currentBoard;
            }
        }
        return fastestBoard.getWinningScore();
    }
    
    public int part2() {
        Board slowestBoard = boards[0];
        int previousMostDraws = slowestBoard.getDrawsToWin();
        for (Board currentBoard : boards) {
            int drawsToWinCurrentBoard = currentBoard.getDrawsToWin();
            if (drawsToWinCurrentBoard > previousMostDraws) {
                previousMostDraws = drawsToWinCurrentBoard;
                slowestBoard = currentBoard;
            }
        }
        return slowestBoard.getWinningScore();
    }
    
    
    //Why is this slower?!
    public void bothParts() {
        Board fastestWin = boards[0];
        Board slowestWin = boards[0];
        int fastestBoard = fastestWin.getDrawsToWin();
        int slowestBoard = slowestWin.getDrawsToWin();
        for (Board currentBoard : boards) {
            int drawsToWinCurrentBoard = currentBoard.getDrawsToWin();
            if (drawsToWinCurrentBoard < fastestBoard) {
                fastestBoard = drawsToWinCurrentBoard;
                fastestWin = currentBoard;
            }
            if (drawsToWinCurrentBoard > slowestBoard) {
                slowestBoard = drawsToWinCurrentBoard;
                slowestWin = currentBoard;
            }
        }
        System.out.printf("Part 1: %d%n" +
                "Part 2: %d%n", fastestWin.getWinningScore(), slowestWin.getWinningScore());
    }
    
    public static void main(String[] args) {
        new Day4();
    }
}
