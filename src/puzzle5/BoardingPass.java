package puzzle5;

public class BoardingPass implements Comparable<BoardingPass>{
    private String originalString;
    private int row = 0;
    private int column = 0;
    private final int seatID;

    public BoardingPass(int seatID) {
        this.seatID = seatID;
        row = seatID / 8;
        column = seatID % 8;
    }

    public BoardingPass(String string) {
        originalString = string;
        calculateRow(string.substring(0, 7));
        calculateColumn(string.substring(7));
        seatID = row * 8 + column;
    }

    private void calculateRow(String rowString) {
        int nextLetterValue = 64;
        for (int i = 0; i < 7; i++) {
            if (rowString.charAt(i) == 'B') {
                row += nextLetterValue;
            }
            nextLetterValue /= 2;
        }
    }

    private void calculateColumn(String columnString) {
        int nextLetterValue = 4;
        for (int i = 0; i < 3; i++) {
            if (columnString.charAt(i) == 'R') {
                column += nextLetterValue;
            }
            nextLetterValue /= 2;
        }
    }

    @Override
    public int compareTo(BoardingPass pass) {
        return seatID - pass.seatID;
    }

    @Override
    public String toString() {
        if (originalString != null) {
            return String.format("row %d, column %d, seat ID %d, original string %s", row, column, seatID, originalString);
        } else {
            return String.format("row %d, column %d, seat ID %d", row, column, seatID);
        }
    }

    public int getSeatID() {
        return seatID;
    }
}
