package Day10to17.puzzle17;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class Cube {
    private int x;
    private int y;
    private int z;
    @Setter
    private boolean isActive;
    @Setter
    private int adjacentActiveCubes = 0;
    
    
    public Cube(int x, int y, int z, boolean isActive) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.isActive = isActive;
    }
    
//    public Cube(int x, int y, int z, boolean isActive, int activations) {
//        this.x = x;
//        this.y = y;
//        this.z = z;
//        this.isActive = isActive;
//        this.activations = activations;
//    }
    
    public void incrementAdjacentActiveCubes() {
        adjacentActiveCubes++;
    }
}
