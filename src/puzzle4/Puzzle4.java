package puzzle4;

import utility.Utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Puzzle4 {
    List<String> rawPassportList = Utility.readStringFile("puzzle4.bat");
    List<Passport> passportList = new ArrayList<>();


    public Puzzle4() {
//        rawPassportList.forEach(System.out::println);
        processPassports();
        System.out.println(countValidPasswords() + " valid passwords.");
    }

    private void processPassports() {
        Passport passport = new Passport();
        for (String line : rawPassportList) {
            if (!line.equals("")) {
                processLine(line, passport);
            } else {
                System.out.println("Adding passport : " + passport);
                passportList.add(passport);
                passport = new Passport();
            }
        }
        System.out.println("Adding final passport: " + passport);
        passportList.add(passport);
    }

    private ArrayList<Passport> readPassportsFromFile() {
        ArrayList<Passport> passportList = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("puzzle4.bat"))) {
            String line;
            Passport passport = new Passport();
            int counter = 0;
            while (scanner.hasNextLine()) {
                while (true) {
                    System.out.println(++counter);
                    line = scanner.nextLine();
                    if (line == null || line.equals("")) {
                        passportList.add(passport);
                        break;
                    }
                    processLine(line, passport);
                }


            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return passportList;
    }

    private void processLine(String line, Passport passport) {
        System.out.println("Processing line " + line);
        String[] split = line.split(" ");
        for (String data : split) {
            passport.setValue(data);
        }
    }

    private long countValidPasswords() {
        return passportList.stream()
                .filter(Passport::isValidPassport)
                .count();
    }

    public static void main(String[] args) {
        new Puzzle4();
    }
}
