package utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utility {
    public static List<Integer> readIntegerFile(String filename) {
        List<Integer> integerList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String nextLine;
            while ((nextLine = reader.readLine()) != null) {
                integerList.add(Integer.parseInt(nextLine));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return integerList;
    }
}
