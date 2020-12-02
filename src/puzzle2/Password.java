package puzzle2;

public class Password {
    private int lowLimit;
    private int highLimit;
    private char requiredChar;
    private String password;

    public Password(int lowLimit, int highLimit, char requiredChar, String password) {
        this.lowLimit = lowLimit;
        this.highLimit = highLimit;
        this.requiredChar = requiredChar;
        this.password = password;
    }
}
