package utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Utility {
    static String folder = "PuzzleInputs\\";

    public static List<Integer> readIntegerFile(String filename) {
        List<Integer> integerList = new ArrayList<>(500);
        try (BufferedReader reader = new BufferedReader(new FileReader(folder + filename))) {
            String nextLine;
            while ((nextLine = reader.readLine()) != null) {
                integerList.add(Integer.parseInt(nextLine));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return integerList;
    }

    public static List<Long> readLongFile(String filename) {
        List<Long> longList = new ArrayList<>(500);
        try (BufferedReader reader = new BufferedReader(new FileReader(folder + filename))) {
            String nextLine;
            while ((nextLine = reader.readLine()) != null) {
                longList.add(Long.parseLong(nextLine));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return longList;
    }


    public static List<String> readStringFile(String filename) {
        List<String> stringList = new ArrayList<>(500);
        try {
            stringList = Files.readAllLines(Path.of(folder + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringList;
    }
}
