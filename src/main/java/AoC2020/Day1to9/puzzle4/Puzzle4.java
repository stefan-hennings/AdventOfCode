package AoC2020.Day1to9.puzzle4;

import utility.ExecutionTime;
import utility.Utility;

import java.util.ArrayList;
import java.util.List;

public class Puzzle4 {
    List<String> rawPassportList = Utility.readStringFile("AoC2020.Day1to9\\puzzle4.bat");
    List<Passport> passportList = new ArrayList<>();


    public Puzzle4() {
        processPassports();
        System.out.println(countValidPasswords() + " valid passwords.");

        System.out.println(countValidPasswordsPart2() + " valid passwords.");
    }

    private void processPassports() {
        Passport passport = new Passport();
        for (String line : rawPassportList) {
            if (!line.equals("")) {
                processLine(line, passport);
            } else {
                passportList.add(passport);
                passport = new Passport();
            }
        }
        passportList.add(passport);
    }

    private void processLine(String line, Passport passport) {
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

    private long countValidPasswordsPart2() {
        return passportList.stream()
                .filter(Passport::isValidPassportForPart2)
                .count();
    }

    public static void main(String[] args) {
        ExecutionTime.start();
        new Puzzle4();
        ExecutionTime.stop();
    }
}
