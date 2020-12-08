package puzzle7;

import utility.ExecutionTime;
import utility.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Puzzle7 {
    static final String filename = "puzzle7";
//    static final String test = "test";
//    static final String test2 = "test2";

    public static List<Bag> allBags = readBags(filename);
    public static final List<Bag> checkedBags = new ArrayList<>();


    private static List<Bag> readBags(String s) {
        return Utility.readStringFile(s).stream()
                .map(string -> string.replaceAll(" contain ", ","))
                .map(string -> string.replace(".", ""))
                .map(Bag::new)
                .collect(Collectors.toList());

    }

    public static void main(String[] args) {
        ExecutionTime.start();
        System.out.println((allBags.stream()
                .filter(bag -> bag.color.equals("shiny gold bag"))
                .map(Bag::getParentBags)
                .reduce(0L, Long::sum) - 1) + " bags contain a shiny gold bag");

        System.out.println("1 shiny gold contains: " + (allBags.stream()
                .filter(bag -> bag.color.equals("shiny gold bag"))
                .map(Bag::countAllSubBags)
                .reduce(0L, Long::sum) - 1));

        ExecutionTime.stop();
    }
}
