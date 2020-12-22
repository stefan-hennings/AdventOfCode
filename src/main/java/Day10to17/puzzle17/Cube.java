package Day10to17.puzzle17;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class Cube {
    private int x;
    private int y;
    private int z;
    @Setter
    private int activations = 0;
    
    @Setter
    private boolean isActive;
    
    public Cube(int x, int y, int z, boolean isActive) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.isActive = isActive;
    }
    
    public Cube(int x, int y, int z, boolean isActive, int activations) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.isActive = isActive;
        this.activations = activations;
    }
    
    public void addActivation() {
        activations++;
    }
}
