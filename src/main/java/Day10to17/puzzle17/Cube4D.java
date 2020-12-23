package Day10to17.puzzle17;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class Cube4D extends Cube{
    private int w;
    
    
    public Cube4D(int x, int y, int z, int w, boolean isActive, int adjacentActiveCubes) {
        super(x, y, z, adjacentActiveCubes);
        this.w = w;
    }
    
    public Cube4D(int x, int y, int z, int w) {
        super(x, y, z);
        this.w = w;
    }
}
