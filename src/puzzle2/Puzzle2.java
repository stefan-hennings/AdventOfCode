package puzzle2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Puzzle2 {
    List<Password> passwordList = new ArrayList<>();
    List<String> rawDataList = utility.Utility.readStringFile("puzzle2");

    public Puzzle2() {
        passwordList = rawDataList.stream()
                .map(line -> line.replace('-', ' ').replace(":", "").split(" "))
                .map(line -> new Password(Integer.parseInt(line[0]),
                        Integer.parseInt(line[1]),
                        line[2].charAt(0),
                        line[3]))
                .collect(Collectors.toList());
        System.out.println(passwordList);
        System.out.println(passwordList.stream()
                .filter(Password::isValidOldPassword)
                .count());
    }

    public static void main(String[] args) {
        new Puzzle2();
    }
}
