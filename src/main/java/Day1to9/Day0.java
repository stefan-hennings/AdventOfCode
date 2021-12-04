package Day1to9;

import utility.ExecutionTime;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day0 {
    String test = "PuzzleInputs/test";
    String real = "src/main/java/Day1to9/input";
    
    int[] input;
    
    {
        try {
            input = Files.readAllLines(Paths.get(real)).stream()
                .mapToInt(Integer::parseInt)
                .toArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Day0(){
        ExecutionTime.start();
    
        System.out.println(part1());
        
        System.out.println(part2());
        
        ExecutionTime.stop();
    }
    
    public int part1() {
        throw new RuntimeException("not implemented");
    }
    
    public int part2() {
        throw new RuntimeException("not implemented");
    }
    
    public static void main(String[] args) {
        new Day0();
    }
}
