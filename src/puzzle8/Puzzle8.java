package puzzle8;

import utility.ExecutionTime;
import utility.Utility;

import java.util.ArrayList;
import java.util.List;

public class Puzzle8 {
    String fileName = "puzzle8";
    String test = "test";
    List<String> instructionList = Utility.readStringFile(fileName);
    int accumulator = 0;
    int instructionIterator = -1;
    int alteredIterator;
    int alteredAcc;
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
        System.out.println("Performing: " + instructionIterator + ": Execute normal " + instruction);
        switch (instruction.substring(0, 3)) {
            case "nop" -> {
            } //Do nothing
            case "acc" -> {
                accumulator += Integer.parseInt(instruction.substring(4));
                System.out.println(accumulator);
            }
            case "jmp" -> instructionIterator += Integer.parseInt(instruction.substring(4)) - 1;
        }
    }

    private void part2() {
        performed = new ArrayList<>(200);
        instructionIterator = -1;
        accumulator = 0;
        String currentInstruction;
        while (true) {
            instructionIterator++;
            if (performed.contains(instructionIterator)) {
                System.out.println("Failure at " + instructionIterator);
            }
            currentInstruction = instructionList.get(instructionIterator);
            performed.add(instructionIterator);

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
        alteredAcc = accumulator;
        List<Integer> performedInAltered = new ArrayList<>(50);

        List<String> alteredList = instructionList;
        if (instructionList.get(instructionIterator).startsWith("jmp")) {
            alteredList.set(instructionIterator, instructionList.get(instructionIterator).replace("jmp", "nop"));
//            alteredList.get(instructionIterator).replace("jmp", "nop");
        } else {
            alteredList.set(instructionIterator, instructionList.get(instructionIterator).replace("nop", "jmp"));
//            alteredList.get(instructionIterator).replace("nop", "jmp");
        }

        instructionList = Utility.readStringFile(fileName);

        System.out.printf("%n%nInstruction %d changed from \"%s\" to \"%s\".%n", instructionIterator, instructionList.get(instructionIterator), alteredList.get(instructionIterator));

        while (true) {
            alteredIterator++;
            if (alteredIterator == instructionList.size()) {
                System.out.printf("The accumulator ended at %d after changing command %d%n", alteredAcc, instructionIterator);
                return true;
            }
            if (performedInAltered.contains(alteredIterator)) {
                System.out.println("Failed at instruction " + alteredIterator + "\n\nAccumulator set back to: " + accumulator);
                return false;
            }
            performedInAltered.add(alteredIterator);
            System.out.print("Performing " + alteredIterator + ": ");
            executeAlteredInstruction(alteredList.get(alteredIterator));
        }
    }

    private void executeAlteredInstruction(String instruction) {
        System.out.println("Execute altered " + instruction);
        switch (instruction.substring(0, 3)) {
            case "nop" -> {
            } //Do nothing
            case "acc" -> {
                alteredAcc += Integer.parseInt(instruction.substring(4));
                System.out.println(alteredAcc);
            }
            case "jmp" -> alteredIterator += Integer.parseInt(instruction.substring(4)) - 1;
        }
    }

    public static void main(String[] args) {
        ExecutionTime.start();
        new Puzzle8();
        ExecutionTime.stop();
    }
}
