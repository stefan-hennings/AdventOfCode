package Day1to9.Day2;

import utility.ExecutionTime;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day2 {
    String test = "PuzzleInputs/test";
    String real = "src/main/java/Day1to9/Day2/input";
    
    List<String> input;
    
    {
        try {
            input = Files.readAllLines(Paths.get(real));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Day2(){
        ExecutionTime.start();
    
        System.out.println(part1());
        
        System.out.println(part2());
        
        ExecutionTime.stop();
    }
    
    public int part1() {
        int horizontal = 0;
        int depth = 0;
        
        for (String line : input) {
            String[] instruction = line.split(" ");
            int change = Integer.parseInt(instruction[1]);
            switch (instruction[0]) {
                case "forward" ->  horizontal += change;
                case "down" ->  depth += change;
                case "up" ->  depth -= change;
            }
        }
        return horizontal * depth;
    }
    
    public int part2() {
        int horizontal = 0;
        int depth = 0;
        int aim = 0;
        
        for (String line : input) {
            String[] instruction = line.split(" ");
            int change = Integer.parseInt(instruction[1]);
            switch (instruction[0]) {
                case "forward" ->  {
                    horizontal += change;
                    depth += aim * change;
                }
                case "down" ->  aim += change;
                case "up" ->  aim -= change;
            }
        }
        return horizontal * depth;
    }
    
    
    public static void main(String[] args) {
        new Day2();
    }
}
