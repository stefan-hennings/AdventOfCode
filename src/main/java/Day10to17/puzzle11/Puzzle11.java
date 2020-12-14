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

    int[][] directions = {
            {-1, -1},
            {-1, 0},
            {-1, 1},
            {0, -1},
            {0, 1},
            {1, -1},
            {1, 0},
            {1, 1}
    };


    public Puzzle11() {
        String file = real;
        seatMap = Utility.readStringFile(file);
        numberOfColumns = seatMap.get(0).length();
        numberOfRows = seatMap.size();
        part1();

        seatMap = Utility.readStringFile(file);
        part2();
    }

    private void part2() {

        hasChanged = true;
        while (hasChanged) {
            hasChanged = false;
            buffer = new ArrayList<>(numberOfRows);

            for (int row = 0; row < numberOfRows; row++) {
                buffer.add(calculateSeatChangesInRowLOS(row));
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
    }

    private String calculateSeatChangesInRowLOS(int row) {
        StringBuilder result = new StringBuilder();
        char seatStatus;
        for (int column = 0; column < numberOfColumns; column++) {
            seatStatus = seatMap.get(row).charAt(column);
            if (seatStatus == '.') {
                result.append(".");
            } else if (seatStatus == 'L') {
                if (seesNoOccupiedSeats(row, column)) {
                    result.append("#");
                    hasChanged = true;
                } else {
                    result.append("L");
                }
            } else { //seatStatus is #
                if (seesFiveOrMoreOccupied(row, column)) {
                    result.append("L");
                    hasChanged = true;
                } else {
                    result.append("#");
                }
            }
        }
        return result.toString();
    }

    private boolean seesNoOccupiedSeats(int sentRow, int sentColumn) {
        for (int[] direction : directions) {
            if (checkSightLine(sentRow, sentColumn, direction) == '#') {
                return false;
            }
        }
        return true;
    }

    private char checkSightLine(int row, int column, int[] direction) {
        row += direction[0];
        column += direction[1];
        while (row >= 0 && row < numberOfRows && column >= 0 && column < numberOfColumns) {
            if (seatMap.get(row).charAt(column) != '.') {
                return seatMap.get(row).charAt(column);
            }
            row += direction[0];
            column += direction[1];
        }
        return '.';
    }

    private boolean seesFiveOrMoreOccupied(int sentRow, int sentColumn) {
        int occupiedSeatsCount = 0;
        for (int[] direction : directions) {
            if (checkSightLine(sentRow, sentColumn, direction) == '#') {
                occupiedSeatsCount++;
                if (occupiedSeatsCount >= 5) {
                    return true;
                }
            }
        }
        return false;
    }

    private void part1() {
        hasChanged = true;
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
                if (hasFourOrMoreAdjacentOccupied(row, column)) {
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

    private boolean hasFourOrMoreAdjacentOccupied(int sentRow, int sentColumn) {
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
