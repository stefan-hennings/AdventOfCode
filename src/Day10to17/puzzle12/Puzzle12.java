package Day10to17.puzzle12;

import utility.ExecutionTime;
import utility.Utility;

import java.util.List;

public class Puzzle12 {
    String test = "test";
    String real = "Day10to17\\puzzle12";

    public static final int NORTH = 0;
    public static final int EAST = 90;
    public static final int SOUTH = 180;
    public static final int WEST = 270;

    List<String> instructions = Utility.readStringFile(real);


    public Puzzle12() {
        part1();
    }

    private void part1() {
        int xPosition = 0;
        int yPosition = 0;
        int heading = EAST;

        for (String instruction : instructions) {
            char instructionType = instruction.charAt(0);
            if (instructionType == 'F') {
                instructionType = switch (heading) {
                    case EAST -> 'E';
                    case SOUTH -> 'S';
                    case WEST -> 'W';
                    case NORTH -> 'N';
                    default -> throw new IllegalStateException("Unexpected value: " + heading);
                };
            }
            switch (instructionType) {
                case 'N' -> yPosition += Integer.parseInt(instruction.substring(1));
                case 'E' -> xPosition += Integer.parseInt(instruction.substring(1));
                case 'S' -> yPosition -= Integer.parseInt(instruction.substring(1));
                case 'W' -> xPosition -= Integer.parseInt(instruction.substring(1));
                case 'R' -> heading = Math.floorMod((heading + Integer.parseInt(instruction.substring(1))), 360);
                case 'L' -> heading = Math.floorMod((heading - Integer.parseInt(instruction.substring(1))), 360);
            }
        }

        System.out.println(Math.abs(xPosition) + Math.abs(yPosition));
    }

    public static void main(String[] args) {
        ExecutionTime.start();
        new Puzzle12();
        ExecutionTime.stop();
    }
}
