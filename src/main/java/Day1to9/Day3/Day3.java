package Day1to9.Day3;

import utility.ExecutionTime;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Day3 {
    String test = "PuzzleInputs/test";
    String real = "src/main/java/Day1to9/Day3/input.txt";
    
    List<String> input;
    
    {
        try {
            input = Files.readAllLines(Paths.get(real));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Day3() {
        ExecutionTime.start();
        
        System.out.println(part1());
        
        System.out.println(part2());
        
        ExecutionTime.stop();
    }
    
    public int part1() {
        int[] counts = new int[input.get(0).length()];
        for (String line : input) {
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '1') {
                    counts[i]++;
                }
            }
        }
        
        StringBuilder mostCommon = new StringBuilder();
        StringBuilder leastCommon = new StringBuilder();
        int halfSize = input.size() / 2;
        for (int oneCount : counts) {
            mostCommon.append(oneCount > halfSize ? '1' : '0');
            leastCommon.append(oneCount > halfSize ? '0' : '1');
        }
        
        int gammaRate = Integer.parseInt(mostCommon.toString(), 2);
        int epsilonRate = Integer.parseInt(leastCommon.toString(), 2);
        
        return gammaRate * epsilonRate;
    }
    
    public int part2() {
        int oxygenRating = findOxygenRating();
        int scrubberRating = findScrubberRating();
        
        return oxygenRating * scrubberRating;
    }
    
    public int findOxygenRating() {
        List<String> candidates = List.copyOf(input);
        for (int position = 0; candidates.size() > 1; position++) {
            int oneCount = countOnesInPosition(candidates, position);
            boolean shouldBeOne = oneCount >= (candidates.size() + 1) / 2;
            candidates = getCandidatesWithXInPosition(candidates, shouldBeOne ? '1' : '0', position);
        }
        return Integer.parseInt(candidates.get(0), 2);
    }
    
    public int findScrubberRating() {
        List<String> candidates = List.copyOf(input);
        for (int position = 0; candidates.size() > 1; position++) {
            int oneCount = countOnesInPosition(candidates, position);
            boolean shouldBeOne = oneCount < (candidates.size() + 1) / 2;
            candidates = getCandidatesWithXInPosition(candidates, shouldBeOne ? '1' : '0', position);
        }
        return Integer.parseInt(candidates.get(0), 2);
    }
    
    public int countOnesInPosition(List<String> list, int position) {
        int count = 0;
        for (String line : list) {
            if (line.charAt(position) == '1') {
                count++;
            }
        }
        return count;
    }
    
    public List<String> getCandidatesWithXInPosition(List<String> candidates, char x, int position) {
        return candidates.stream()
                .filter(line -> line.charAt(position) == x)
                .collect(Collectors.toList());
    }
    
    public static void main(String[] args) {
        new Day3();
    }
}
