package puzzle7;

import utility.ExecutionTime;
import utility.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Puzzle7 {
    public static final List<Bag> allBags = readBags();
    public static final List<Bag> checkedBags = new ArrayList<>();

    static final String filename = "puzzle7";
//    static final String filename = "test";

    private static List<Bag> readBags() {
        return Utility.readStringFile(filename).stream()
                .map(string -> string.replaceAll(" contain ", ","))
                .map(string -> string.replace(".", ""))
                .map(Bag::new)
                .collect(Collectors.toList());

    }

    public static void main(String[] args) {
        ExecutionTime.start();
        System.out.println(allBags.stream()
                .filter(bag -> bag.color.equals("shiny gold bag"))
                .map(Bag::getParentBags)
                .reduce(0L, Long::sum)-1);
        System.out.println(allBags.stream()
                .filter(bag -> bag.color.equals("shiny gold bag"))
                .findFirst());
        ExecutionTime.stop();
    }
}
