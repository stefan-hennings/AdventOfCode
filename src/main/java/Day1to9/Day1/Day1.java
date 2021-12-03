package Day1to9.Day1;

import utility.ExecutionTime;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day1 {
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
    
    public Day1(){
        ExecutionTime.start();
    
        System.out.println(part1(1));
        
        System.out.println(part1(3));
        
        ExecutionTime.stop();
    }
    
    public int part1(int offset) {
        int increases = 0;
        for (int i = offset; i < input.length; i++) {
            if (input[i-offset] < input[i]) {
                increases++;
            }
        }
        return increases;
    }
    
    
    public static void main(String[] args) {
        new Day1();
    }
}
