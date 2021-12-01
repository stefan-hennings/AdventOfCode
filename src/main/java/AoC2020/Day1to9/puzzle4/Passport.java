package AoC2020.Day1to9.puzzle4;

public class Passport {
    String height, hairColor, eyeColor, passportId, countryId;
    int birthYear, issueYear, expirationYear;

    public void setValue(String string) {
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
            default -> throw new IllegalArgumentException(input + " is not a valid day1");
        }
    }

    public boolean isValidPassport() {
        return birthYear != 0 && issueYear != 0 && expirationYear != 0 && height != null
                && hairColor != null && eyeColor != null && passportId != null;
    }

    public boolean isValidPassportForPart2() {
        return hasValidBirthDate() && hasValidIssueDate() && hasValidExpirationDate() && hasValidHeight()
                && hasValidHairColor() && hasValidEyeColor() && hasValidPassportId();
    }

    private boolean hasValidBirthDate() {
        return birthYear >= 1920 && birthYear <= 2002;
    }

    private boolean hasValidIssueDate() {
        return issueYear >= 2010 && issueYear <= 2020;
    }

    private boolean hasValidExpirationDate() {
        return expirationYear >= 2020 && expirationYear <= 2030;
    }

    private boolean hasValidHeight() {
        int intHeight;
        try {
            intHeight = Integer.parseInt(height.substring(0, height.length() - 2));
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        if (height.endsWith("cm")) {
            return  intHeight >= 150 && intHeight <= 193;
        } else if (height.endsWith("in")) {
            return  intHeight >= 59 && intHeight <= 76;
        }
        return false;
    }

    private boolean hasValidHairColor() {
        if (hairColor == null || hairColor.length() != 7 || hairColor.charAt(0) != '#') {
            return false;
        }
        String validNumbers = "0123456789abcdef";
        for (int i = 1; i < 7; i++) {
            if (validNumbers.indexOf(hairColor.charAt(i)) == -1) {
                return false;
            }
        }
        return true;
    }

    private boolean hasValidEyeColor() {
        return eyeColor != null && (eyeColor.equals("amb") || eyeColor.equals("blu") || eyeColor.equals("gry")
                || eyeColor.equals("grn") || eyeColor.equals("hzl") || eyeColor.equals("oth") || eyeColor.equals("brn"));
    }

    private boolean hasValidPassportId() {
        if (passportId == null || passportId.length() != 9) {
            return false;
        }
        for (int i = 0; i < 9; i++) {
            if (!Character.isDigit(passportId.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
