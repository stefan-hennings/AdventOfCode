package puzzle5;

import utility.Utility;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Puzzle5 {
    List<BoardingPass> boardingPassList;

    public Puzzle5() {
        boardingPassList = Utility.readStringFile("puzzle5").stream()
                .map(BoardingPass::new)
                .sorted()
                .collect(Collectors.toList());

        System.out.println("The highest seat ID is " + boardingPassList.get(boardingPassList.size() - 1));
        System.out.println("My seat ID is " + findMySeatID());
    }

    private int findMySeatID() {
        for (int i = 0; i < boardingPassList.size(); i++) {
            if (boardingPassList.get(i+1).seatID - boardingPassList.get(i).seatID == 2) {
                return boardingPassList.get(i).seatID + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        new Puzzle5();
    }
}
