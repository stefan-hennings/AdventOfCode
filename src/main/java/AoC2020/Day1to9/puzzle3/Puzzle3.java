package AoC2020.Day1to9.puzzle3;

import utility.ExecutionTime;
import utility.Utility;

import java.util.List;

public class Puzzle3 {
    String filename = "AoC2020.Day1to9\\puzzle3";
    private final List<String> map = Utility.readStringFile(filename);

    public Puzzle3() {
        List<String> slopes = Utility.readStringFile(filename + "Part2Slopes");
        System.out.println(checkSlope(3, 1));

        System.out.println(checkMultipleSlopes(slopes));
    }

    private int checkSlope(int right, int down) {
        Sled sled = new Sled(map.get(0).length());
        int treeCounter = 0;
        int row;
        int column;
        while (sled.getRow() < map.size() - 1) {
            sled.move(right, down);
            row = sled.getRow();
            column = sled.getColumn();
            if (map.get(row).charAt(column) == '#') {
                treeCounter++;
            }
        }
        return treeCounter;
    }

    private long checkMultipleSlopes(List<String> slopes) {
        long product = 1;
        for (String slope : slopes) {
            product *= checkSlope(Character.getNumericValue(slope.charAt(6)), Character.getNumericValue(slope.charAt(14)));
        }
        return product;
    }

    public static void main(String[] args) {
        ExecutionTime.start();
        new Puzzle3();
        ExecutionTime.stop();
    }
}
