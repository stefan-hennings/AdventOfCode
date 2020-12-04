package puzzle3;

public class Sled {
    private int row;
    private int column;

    public Sled() {
        this.row = 0;
        this.column = 0;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setPosition(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public void move(int right, int down) {
        this.row += right;
        this.column += down;
    }
}
