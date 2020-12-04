package puzzle3;

import utility.Utility;

import java.util.List;

public class Puzzle3 {
    private final List<String> map = Utility.readStringFile("puzzle3");

    public Puzzle3() {
        part1();
    }

    private void part1() {
        Sled sled = new Sled(map.get(0).length());
        int treeCounter = 0;
        int row;
        int column;
        while (sled.getRow() < map.size() - 1) {
            sled.move(3, 1);
            row = sled.getRow();
            column = sled.getColumn();
            if (map.get(row).charAt(column) == '#') {
                treeCounter++;
            }
        }
        System.out.println("The sled hit " + treeCounter + " trees.");
    }

    public static void main(String[] args) {
        new Puzzle3();
    }
}
