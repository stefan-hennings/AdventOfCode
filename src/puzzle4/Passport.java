package puzzle4;

public class Passport {
    String height, hairColor, eyeColor, passportId, countryId;
    int birthYear, issueYear, expirationYear;

    public Passport() {
    }

    @Override
    public String toString() {
        return "Passport{" +
                "birthYear='" + birthYear + '\'' +
                ", issueYear='" + issueYear + '\'' +
                ", expirationYear='" + expirationYear + '\'' +
                ", height='" + height + '\'' +
                ", hairColor='" + hairColor + '\'' +
                ", eyeColor='" + eyeColor + '\'' +
                ", passportId='" + passportId + '\'' +
                ", countryId='" + countryId + '\'' +
                '}';
    }

    public void setValue(String string) {
        System.out.println("Setting value: " + string);
        String type = string.substring(0, 3);
        String input = string.substring(4);
        switch (type) {
            case "byr" -> birthYear = Integer.parseInt(input);
            case "iyr" -> issueYear = Integer.parseInt(input);
            case "eyr" -> expirationYear = Integer.parseInt(input);
            case "hgt" -> height = input;
            case "hcl" -> hairColor = input;
            case "ecl" -> eyeColor = input;
            case "pid" -> passportId = input;
            case "cid" -> countryId = input;
            default -> throw new IllegalArgumentException(input + " is not a valid input");
        }
    }

    public boolean isValidPassport() {
        return birthYear != 0 && issueYear != 0 && expirationYear != 0 && height != null
                && hairColor != null && eyeColor != null && passportId != null;
    }

    public boolean isValidPassportForPart2() {
        return  height != null
                && hairColor != null && eyeColor != null && passportId != null;
    }

    private boolean hasValidDates() {

        return true;
    }
}
