package AoC2020.Day1to9.puzzle3;

import lombok.Getter;

@Getter
public class Sled {
    private int row;
    private int column;
    private final int mapWidth;

    public Sled(int mapWidth) {
        this.row = 0;
        this.column = 0;
        this.mapWidth = mapWidth;
    }

    @Override
    public String toString() {
        return "Sled{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }

    public void move(int right, int down) {
        column = (column + right) % mapWidth;
//        if (column >= mapWidth) {
//            column -= mapWidth;
//        }
        this.row += down;
    }
}
