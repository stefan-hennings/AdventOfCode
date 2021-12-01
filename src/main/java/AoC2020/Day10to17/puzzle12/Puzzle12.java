package AoC2020.Day10to17.puzzle12;

import utility.ExecutionTime;
import utility.Utility;

import java.util.List;

public class Puzzle12 {
    String test = "test";
    String real = "AoC2020.Day10to17\\puzzle12";

    public static final int NORTH = 0;
    public static final int EAST = 90;
    public static final int SOUTH = 180;
    public static final int WEST = 270;

    List<String> instructions = Utility.readStringFile(real);


    public Puzzle12() {
        part1();

        part2();
    }

    private void part1() {
        int xPosition = 0;
        int yPosition = 0;
        int heading = EAST;

        for (String instruction : instructions) {
            char instructionType = instruction.charAt(0);
            int change = Integer.parseInt(instruction.substring(1));
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
                case 'N' -> yPosition += change;
                case 'E' -> xPosition += change;
                case 'S' -> yPosition -= change;
                case 'W' -> xPosition -= change;
                case 'R' -> heading = Math.floorMod((heading + change), 360);
                case 'L' -> heading = Math.floorMod((heading - change), 360);
            }
        }

        System.out.printf("Position: East: %d, North: %d%n", xPosition, yPosition);
        System.out.println("Distance: " + (Math.abs(xPosition) + Math.abs(yPosition)) + "\n");
    }

    private void part2() {
        int xPosition = 0;
        int yPosition = 0;
        int xWaypoint = 10;
        int yWaypoint = 1;

        for (String instruction : instructions) {
            char instructionType = instruction.charAt(0);
            int change = Integer.parseInt(instruction.substring(1));
            switch (instructionType) {
                case 'N' -> yWaypoint += change;
                case 'E' -> xWaypoint += change;
                case 'S' -> yWaypoint -= change;
                case 'W' -> xWaypoint -= change;
                case 'R' -> {
                    for (int i = 0; i < change / 90; i++) {
                        int temp = yWaypoint;
                        yWaypoint = xWaypoint * -1;
                        xWaypoint = temp;
                    }
                }
                case 'L' -> {
                    for (int i = 0; i < change / 90; i++) {
                        int temp = xWaypoint;
                        xWaypoint = yWaypoint * -1;
                        yWaypoint = temp;
                    }
                }
                case 'F' -> {
                    xPosition += xWaypoint * change;
                    yPosition += yWaypoint * change;
                }
            }
        }
        System.out.printf("Position: East: %d, North: %d%n", xPosition, yPosition);
        System.out.printf("Waypoint: East: %d, North: %d%n", xWaypoint, yWaypoint);
        System.out.println("Distance: " + (Math.abs(xPosition) + Math.abs(yPosition)) + "\n");
    }

    public static void main(String[] args) {
        ExecutionTime.start();
        new Puzzle12();
        ExecutionTime.stop();
    }
}
