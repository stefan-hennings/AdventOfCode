package Day10to17.puzzle17;

import lombok.Getter;
import lombok.Setter;

@Getter

public class Cube {
    private int x;
    private int y;
    private int z;
    
    @Setter
    private boolean isActive = true;
    
    public Cube(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public Cube(int x, int y, int z, boolean isActive) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.isActive = isActive;
    }
}
