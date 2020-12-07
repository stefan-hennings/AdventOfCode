package puzzle7;

import utility.ExecutionTime;
import utility.Utility;

import java.util.List;
import java.util.stream.Collectors;

public class Puzzle7 {
    static final List<Bag> allBags = readBags();

    public Puzzle7() {
        allBags.forEach(System.out::println);
    }

    private static List<Bag> readBags() {
        return Utility.readStringFile("puzzle7").stream()
                .map(string -> string.replaceAll(" contain ", ","))
                .map(string -> string.replace(".", ""))
                .map(Bag::new)
                .collect(Collectors.toList());

    }

    public static void main(String[] args) {
        ExecutionTime.start();
        new Puzzle7();
        ExecutionTime.stop();
    }
}
