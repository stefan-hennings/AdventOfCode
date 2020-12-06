package puzzle5;

import utility.Utility;

import java.util.List;
import java.util.stream.Collectors;

public class Puzzle5 {
    List<BoardingPass> boardingPassList;
    List<Integer> seatIdList;

    public Puzzle5() {
//        nonBinaryMethod();
        binaryMethod();
    }

    private void binaryMethod() {
        seatIdList = Utility.readStringFile("puzzle5").stream()
                .map(this::calculateSeatId)
                .sorted()
                .collect(Collectors.toList());

        System.out.println("Max seat ID is " + seatIdList.get(seatIdList.size() - 1));

        for (int i = 0; i < seatIdList.size(); i++) {
            if (seatIdList.get(i+1) - seatIdList.get(i) == 2) {
                System.out.println("Your seat ID is " + (seatIdList.get(i) + 1));
                break;
            }
        }
    }

    private int calculateSeatId(String seatString) {
        return Integer.parseInt(seatString.replaceAll("[FL]", "0").replaceAll("[BR]", "1"), 2);
    }

    private void nonBinaryMethod() {
        boardingPassList = Utility.readStringFile("puzzle5").stream()
                .map(BoardingPass::new)
                .sorted()
                .collect(Collectors.toList());

        System.out.println("The highest seat ID is " + boardingPassList.get(boardingPassList.size() - 1));
        System.out.println("My seat is " + findMySeatID());
    }

    private BoardingPass findMySeatID() {
        for (int i = 0; i < boardingPassList.size(); i++) {
            if (boardingPassList.get(i+1).getSeatID() - boardingPassList.get(i).getSeatID() == 2) {
                return new BoardingPass(boardingPassList.get(i).getSeatID() + 1);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        new Puzzle5();
    }
}
