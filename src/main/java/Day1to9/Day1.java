package Day1to9;

import utility.ExecutionTime;
import utility.Utility;

public class Day1 {
    String test = "test";
    String real = "Day1to9\\day1";
    
//    List<Integer> input = Utility.readIntegerFile(real);
    int[] input = Utility.readIntegerFile(real).stream()
        .mapToInt(Integer::intValue)
        .toArray();
    
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
