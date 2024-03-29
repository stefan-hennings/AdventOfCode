package AoC2020.Day1to9.puzzle7;

import utility.ExecutionTime;
import utility.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Puzzle7 {
    static final String filename = "AoC2020.Day1to9\\puzzle7";

    private static final List<Bag> allBags = readBags(filename);

    public static List<Bag> getAllBags() {
        return allBags;
    }

    public static List<Bag> getCheckedBags() {
        return checkedBags;
    }

    private static final List<Bag> checkedBags = new ArrayList<>();


    private static List<Bag> readBags(String fileName) {
        return Utility.readStringFile(fileName).stream()
                .map(string -> string.replaceAll(" contain ", ","))
                .map(string -> string.replace(".", ""))
                .map(Bag::new)
                .collect(Collectors.toList());

    }

    public static void main(String[] args) {
        ExecutionTime.start();

        System.out.println((allBags.stream()
                .filter(bag -> bag.getColor().equals("shiny gold bag"))
                .map(Bag::getParentBags)
                .reduce(0L, Long::sum) - 1) + " bags contain a shiny gold bag");

        System.out.println("1 shiny gold contains: " + (allBags.stream()
                .filter(bag -> bag.getColor().equals("shiny gold bag"))
                .map(Bag::countAllSubBags)
                .reduce(0L, Long::sum) - 1));

        ExecutionTime.stop();
    }
}
