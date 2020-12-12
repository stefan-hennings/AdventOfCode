package Day10to17.puzzle11;

import utility.ExecutionTime;
import utility.Utility;

import java.util.ArrayList;
import java.util.List;

public class Puzzle11 {
    String test = "test";
    String real = "Day10to17\\puzzle11";

    List<String> seatMap;
    List<String> buffer;

    int numberOfColumns;
    int numberOfRows;
    boolean hasChanged = true;


    public Puzzle11() {
        seatMap = Utility.readStringFile(real);
        numberOfColumns = seatMap.get(0).length();
        numberOfRows = seatMap.size();

        part1();
    }

    private void part1() {

        while (hasChanged) {
            hasChanged = false;
            buffer = new ArrayList<>(numberOfRows);

            for (int row = 0; row < numberOfRows; row++) {
                buffer.add(calculateSeatChangesInRow(row));
            }
            seatMap = buffer;

        }

        int occupiedSeatCount = 0;
        for (String row : seatMap) {
            for (char seat : row.toCharArray()) {
                if (seat == '#') {
                    occupiedSeatCount++;
                }
            }
        }
        System.out.println(occupiedSeatCount);
        System.out.printf("%d, %d", numberOfRows, numberOfColumns);
    }

    private String calculateSeatChangesInRow(int row) {
        StringBuilder result = new StringBuilder();
        char seatStatus;
        for (int column = 0; column < numberOfColumns; column++) {
            seatStatus = seatMap.get(row).charAt(column);
            if (seatStatus == '.') {
                result.append(".");
            } else if (seatStatus == 'L') {
                if (hasNoAdjacentOccupied(row, column)) {
                    result.append("#");
                    hasChanged = true;
                } else {
                    result.append("L");
                }

            } else { //seatStatus is #
                if (hasMoreThanFourAdjacentOccupied(row, column)) {
                    result.append("L");
                    hasChanged = true;
                } else {
                    result.append("#");
                }
            }
        }
        return result.toString();
    }

    private boolean hasNoAdjacentOccupied(int sentRow, int sentColumn) {
        for (int row = Math.max(sentRow - 1, 0); row < Math.min(sentRow + 2, numberOfRows); row++) {
            for (int column = Math.max(sentColumn - 1, 0); column < Math.min(sentColumn + 2, numberOfColumns); column++) {
                if (!(row == sentRow && column == sentColumn) && seatMap.get(row).charAt(column) == '#') {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean hasMoreThanFourAdjacentOccupied(int sentRow, int sentColumn) {
        int occupiedSeatsCount = 0;
        for (int row = Math.max(sentRow - 1, 0); row < Math.min(sentRow + 2, numberOfRows); row++) {
            for (int column = Math.max(sentColumn - 1, 0); column < Math.min(sentColumn + 2, numberOfColumns); column++) {
                if (!(row == sentRow && column == sentColumn ) && seatMap.get(row).charAt(column) == '#') {
                    occupiedSeatsCount++;
                    if (occupiedSeatsCount >= 4) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ExecutionTime.start();
        new Puzzle11();
        ExecutionTime.stop();
    }
}
