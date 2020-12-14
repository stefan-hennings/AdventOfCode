package Day10to17.puzzle13;

public class Bus {
    int id;
    int offset;
    long bigM;
    long y;

    public Bus(int id, int offset) {
        this.id = id;
        this.offset = offset;
    }

    public long getBigM() {
        return bigM;
    }

    public void setBigM(long bigM) {
        this.bigM = bigM;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }
}
