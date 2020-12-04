package puzzle4;

public class Passport {
    String birthYear, issueYear, expirationYear, height, hairColor, eyeColor, passportId, countryId;

    public Passport() {
    }

    public void setValue(String string) {
        String type = string.substring(0, 3);
        String input = string.substring(3);
        switch (type) {
            case "byr" -> birthYear = input;
            case "iyr" -> issueYear = input;
            case "eyr" -> expirationYear = input;
            case "hgt" -> height = input;
            case "hcl" -> hairColor = input;
            case "ecl" -> eyeColor = input;
            case "pid" -> passportId = input;
            case "cid" -> countryId = input;
            default -> throw new IllegalArgumentException(input + " is not a valid input");
        }
    }

    public boolean isValidPassport() {
        return birthYear != null && issueYear != null && expirationYear != null && height != null
                && hairColor != null && eyeColor != null && passportId != null;
    }
}
