package AoC2020.Day10to17.puzzle14;

import utility.ExecutionTime;
import utility.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Puzzle14 {
    String test = "test";
    String real = "AoC2020.Day10to17\\puzzle14";
    
    List<String> allInstructions;

    public Puzzle14() {
        allInstructions = Utility.readStringFile(real);
        
        part1();

        part2();
    }

    private void part1() {
        String mask = "";
        Map<Integer, Long> memory = new HashMap<>();
        for (String instruction : allInstructions) {
            int equalsIndex = instruction.indexOf("=");
            String type = instruction.substring(0, equalsIndex - 1);
            if (type.equals("mask")) {
                mask = instruction.substring(equalsIndex + 2);
            } else {
                String beforeMask = Integer.toBinaryString(Integer.parseInt(instruction.substring(equalsIndex + 2)));
                beforeMask = String.format("%36s", beforeMask).replace(" ", "0");
                StringBuilder afterMask = new StringBuilder(mask.length());
                for (int i = 0; i < mask.length(); i++) {
                    afterMask.append(mask.charAt(i) != 'X' ? mask.charAt(i) : beforeMask.charAt(i));
                }
                
                int addressStart = instruction.indexOf("[") + 1;
                int addressEnd = instruction.indexOf("]") - 1;
                Integer address = Integer.parseInt(instruction.substring(addressStart, addressEnd + 1));
                memory.put(address, Long.parseLong(afterMask.toString(), 2));
            }
        }
        long result = 0;
        for (Integer key : memory.keySet()) {
            result += memory.get(key);
        }
    
        System.out.println("Part 1: " + result);
    }

    private void part2() {
        String mask = "";
        Map<Long, Integer> memory = new HashMap<>();
        for (String instruction : allInstructions) {
            int equalsIndex = instruction.indexOf("=");
            String type = instruction.substring(0, equalsIndex - 1);
            if (type.equals("mask")) {
                mask = instruction.substring(equalsIndex + 2);
            } else {

                int addressStart = instruction.indexOf("[") + 1;
                int addressEnd = instruction.indexOf("]") - 1;
                String beforeMask = Integer.toBinaryString(Integer.parseInt(instruction.substring(addressStart, addressEnd + 1)));
                beforeMask = String.format("%36s", beforeMask).replace(" ", "0");
                
                StringBuilder afterMask = new StringBuilder();
                for (int i = 0; i < mask.length(); i++) {
                    afterMask.append(mask.charAt(i) != '0' ? mask.charAt(i) : beforeMask.charAt(i));
                }
                int xCount = 0;
                for (int i = 0; i < afterMask.length(); i++) {
                    if (afterMask.charAt(i) == 'X') {
                        xCount++;
                    }
                }
                List<StringBuilder> variations = getAddressVariations(xCount);
                
                List<String> addresses = new ArrayList<>();
                for (StringBuilder variation : variations) {
                    StringBuilder current = new StringBuilder(afterMask);
                    int counter = 0;
                    for (int i = 0; i < current.length(); i++) {
                        if (current.charAt(i) == 'X') {
                            current.setCharAt(i, variation.charAt(counter));
                            counter++;
                        }
                    }
                    addresses.add(current.toString());
                }
                
                int valueToWrite = Integer.parseInt(instruction.substring(equalsIndex + 2));
                for (String address : addresses) {
                    memory.put(Long.parseLong(address, 2), valueToWrite);
                }
            }
        }
        long result = 0;
        for (Long key : memory.keySet()) {
            result += memory.get(key);
        }
    
        System.out.println("Part 2: " + result);
    }
    
    private List<StringBuilder> getAddressVariations(int digitsToAdd) {
        List<StringBuilder> mainList = new ArrayList<>();
        mainList.add(new StringBuilder(digitsToAdd));
    
        for (int digitsAdded = 0; digitsAdded < digitsToAdd; digitsAdded++) {
            List<StringBuilder> copyList = new ArrayList<>();
            for (StringBuilder original : mainList) {
                copyList.add(new StringBuilder(original));
                original.append(1);
            }
            for (StringBuilder copy : copyList) {
                copy.append(0);
                mainList.add(copy);
            }
        }
        return mainList;
    }

    public static void main(String[] args) {
        ExecutionTime.start();
        new Puzzle14();
        ExecutionTime.stop();
    }
}
