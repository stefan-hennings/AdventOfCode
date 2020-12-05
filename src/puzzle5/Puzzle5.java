package puzzle5;

import utility.Utility;

import java.util.List;
import java.util.stream.Collectors;

public class Puzzle5 {
    List<BoardingPass> boardingPassList;

    public Puzzle5() {
        boardingPassList = Utility.readStringFile("puzzle5").stream()
                .map(BoardingPass::new)
                .collect(Collectors.toList());

        System.out.println("The highest seat ID is " + findMaxSeatId());

    }

    private int findMaxSeatId() {
        int max = 0;
        for (BoardingPass pass : boardingPassList) {
            max = Math.max(pass.getSeatID(), max);
        }
        return max;
    }

    public static void main(String[] args) {
        new Puzzle5();
    }
}
