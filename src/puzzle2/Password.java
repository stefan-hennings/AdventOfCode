package puzzle2;

public class Password {
    private final int lowLimit;
    private final int highLimit;
    private final char requiredChar;
    private final String password;

    public Password(int lowLimit, int highLimit, char requiredChar, String password) {
        this.lowLimit = lowLimit;
        this.highLimit = highLimit;
        this.requiredChar = requiredChar;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Password{" +
                "lowLimit=" + lowLimit +
                ", highLimit=" + highLimit +
                ", requiredChar=" + requiredChar +
                ", password='" + password + '\'' +
                '}';
    }


    public boolean isValidOldPassword() {
        int counter = 0;
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) == requiredChar) {
                counter++;
            }
        }

        return (counter >= lowLimit && counter <= highLimit);
    }

    public boolean isValidNewPassword() {
        return (password.charAt(lowLimit - 1) == requiredChar ^ password.charAt(highLimit - 1) == requiredChar);
    }
}