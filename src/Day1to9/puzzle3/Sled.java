package Day1to9.puzzle3;

public class Sled {
    private int row;
    private int column;
    private int mapWidth;

    public Sled(int mapWidth) {
        this.row = 0;
        this.column = 0;
        this.mapWidth = mapWidth;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return "Sled{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }

    public void setPosition(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public void move(int right, int down) {
        column += right;
        if (column >= mapWidth) {
            column -= mapWidth;
        }
        this.row += down;
    }
}
