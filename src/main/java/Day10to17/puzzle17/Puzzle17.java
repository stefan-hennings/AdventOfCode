package Day10to17.puzzle17;

import utility.ExecutionTime;
import utility.Utility;

import java.util.ArrayList;
import java.util.List;

public class Puzzle17 {
    String test = "PuzzleInputs\\test";
    String real = "PuzzleInputs\\Day10to17\\puzzle17";
    
    List<String> startingSlice;

    public Puzzle17() {
        startingSlice = Utility.readStringFile(test);
        
        part1();

        part2();
    }

    private void part1() {
        List<Cube> activeCubes = new ArrayList<>();
        for (int y = 0; y < startingSlice.size(); y++) {
            for (int x = 0; x < startingSlice.get(y).length(); x++) {
                if (startingSlice.get(y).charAt(x) == '#') {
                    activeCubes.add(new Cube(x, y, 0));
                }
            }
        }
        
    }

    private void part2() {

    }

    public static void main(String[] args) {
        ExecutionTime.start();
        new Puzzle17();
        ExecutionTime.stop();
    }
}
