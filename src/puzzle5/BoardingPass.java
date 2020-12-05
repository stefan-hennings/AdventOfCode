package puzzle5;

public class BoardingPass {
    int row = 0;
    int column = 0;
    int seatID;

    public BoardingPass(String string) {
        calculateRow(string.substring(0, 7));
        calculateColumn(string.substring(7));
        seatID = row * column;
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
        int nextLetterValue = 8;
        for (int i = 0; i < 3; i++) {
            if (columnString.charAt(i) == 'R') {
                column += nextLetterValue;
            }
            nextLetterValue /= 2;
        }
    }
}
