package Day10to17.puzzle14;

import utility.ExecutionTime;
import utility.Utility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Puzzle14 {
    String test = "test";
    String real = "Day10to17\\puzzle14";
    
    List<String> allInstructions;

    public Puzzle14() {
        allInstructions = Utility.readStringFile(real);
        
        part1();

        part2();
    }

    private void part1() {
        String mask = "";
        Map<Integer, Long> memory = new HashMap<>();
        int counter = 0;
        for (String instruction : allInstructions) {
            int equalsIndex = instruction.indexOf("=");
            String type = instruction.substring(0, equalsIndex - 1);
            if (type.equals("mask")) {
                mask = instruction.substring(equalsIndex + 2);
                counter++;
            } else {
                String beforeMask = Integer.toBinaryString(Integer.parseInt(instruction.substring(equalsIndex + 2)));
                beforeMask = String.format("%36s", beforeMask).replace(" ", "0");
                StringBuilder afterMask = new StringBuilder();
                for (int i = 0; i < mask.length(); i++) {
                    afterMask.append(mask.charAt(i) != 'X' ? mask.charAt(i) : beforeMask.charAt(i));
                }
                
                int adressStart = instruction.indexOf("[") + 1;
                int adressEnd = instruction.indexOf("]") - 1;
                Integer adress = Integer.parseInt(instruction.substring(adressStart, adressEnd + 1));
                memory.put(adress, Long.parseLong(afterMask.toString(), 2));
            }
        }
        long result = 0;
        for (Integer key : memory.keySet()) {
            result += memory.get(key);
        }
    
        System.out.println("Part 1: " + result);
    }

    private void part2() {

    }

    public static void main(String[] args) {
        ExecutionTime.start();
        new Puzzle14();
        ExecutionTime.stop();
    }
}
