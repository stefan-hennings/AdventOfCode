package Day1to9.puzzle8;

import utility.ExecutionTime;
import utility.Utility;

import java.util.ArrayList;
import java.util.List;

public class Puzzle8 {
    String fileName = "Day1to9\\puzzle8";
    String test = "test";
    List<String> instructionList = Utility.readStringFile(fileName);
    int accumulator = 0;
    int instructionIterator = -1;
    int alteredIterator;
    int alteredAccumulator;
    List<Integer> performed = new ArrayList<>(200);

    public Puzzle8() {
        part1();
        part2();
    }

    private void part1() {
        while (true) {
            instructionIterator++;
            if (performed.contains(instructionIterator)) {
                System.out.printf("The accumulator ended at %d after trying to execute command %d again%n", accumulator, instructionIterator);
                break;
            }
            performed.add(instructionIterator);
            executeInstruction(instructionList.get(instructionIterator));
        }
    }

    private void executeInstruction(String instruction) {
        switch (instruction.substring(0, 3)) {
            case "nop" -> { //Do nothing
            }
            case "acc" -> accumulator += Integer.parseInt(instruction.substring(4));
            case "jmp" -> instructionIterator += Integer.parseInt(instruction.substring(4)) - 1;
        }
    }

    private void part2() {
        instructionIterator = -1;
        accumulator = 0;
        String currentInstruction;
        while (true) {
            instructionIterator++;
            currentInstruction = instructionList.get(instructionIterator);

            if (currentInstruction.startsWith("acc")) {
                executeInstruction(instructionList.get(instructionIterator));
            } else if (tryWithAlteredCommand()) {
                return;
            } else {
                executeInstruction(instructionList.get(instructionIterator));
            }
        }
    }

    private boolean tryWithAlteredCommand() {
        alteredIterator = instructionIterator - 1;
        alteredAccumulator = accumulator;
        List<Integer> performedInAltered = new ArrayList<>(50);

        List<String> alteredList = new ArrayList<>(instructionList);
        if (instructionList.get(instructionIterator).startsWith("jmp")) {
            alteredList.set(instructionIterator, alteredList.get(instructionIterator).replace("jmp", "nop"));
        } else {
            alteredList.set(instructionIterator, alteredList.get(instructionIterator).replace("nop", "jmp"));
        }

        while (true) {
            alteredIterator++;
            if (alteredIterator == instructionList.size()) {
                System.out.printf("The accumulator ended at %d after changing command %d%n", alteredAccumulator, instructionIterator);
                return true;
            }
            if (performedInAltered.contains(alteredIterator)) {
                return false;
            }
            performedInAltered.add(alteredIterator);
            executeAlteredInstruction(alteredList.get(alteredIterator));
        }
    }

    private void executeAlteredInstruction(String instruction) {
        switch (instruction.substring(0, 3)) {
            case "nop" -> { //Do nothing
            }
            case "acc" -> alteredAccumulator += Integer.parseInt(instruction.substring(4));
            case "jmp" -> alteredIterator += Integer.parseInt(instruction.substring(4)) - 1;
        }
    }

    public static void main(String[] args) {
        ExecutionTime.start();
        new Puzzle8();
        ExecutionTime.stop();
    }
}
