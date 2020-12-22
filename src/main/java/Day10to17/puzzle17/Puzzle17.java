package Day10to17.puzzle17;

import utility.ExecutionTime;
import utility.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Puzzle17 {
    String test = "test";
    String real = "Day10to17\\puzzle17";
    
    List<String> startingSlice;
    
    List<Cube> activeCubes;
    List<Cube> adjacentCubes;
    
    public Puzzle17() {
        startingSlice = Utility.readStringFile(test);
        
        part1();
        
        part2();
    }
    
    private void part1() {
        activeCubes = new ArrayList<>();
        for (int y = 0; y < startingSlice.size(); y++) {
            for (int x = 0; x < startingSlice.get(y).length(); x++) {
                if (startingSlice.get(y).charAt(x) == '#') {
                    activeCubes.add(new Cube(x, y, 0, true));
                }
            }
        }
    
        for (int i = 0; i < 6; i++) {
            adjacentCubes = new ArrayList<>();
            for (Cube activeCube : activeCubes) {
                processAdjacentCubes(activeCube);
            }
        
            activeCubes.removeIf(cube -> cube.getActivations() == 2 || cube.getActivations() == 3);
        
            for (Cube cube : adjacentCubes) {
                if (cube.getActivations() == 3) {
                    cube.setActive(true);
                    activeCubes.add(cube);
                }
            }
        
            for (Cube activeCube : activeCubes) {
                activeCube.setActivations(0);
            }
        }
    
        System.out.println(activeCubes.size());
    }
    
    private void processAdjacentCubes(Cube activeCube) {
        for (int zOffset = -1; zOffset <= 1; zOffset++) {
            for (int yOffset = -1; yOffset <= 1; yOffset++) {
                for (int xOffset = -1; xOffset <= 1; xOffset++) {
                    if (xOffset == 0 && yOffset == 0 && zOffset == 0) {
                        continue;
                    }
                    processCube(activeCube.getX() - xOffset, activeCube.getY() - yOffset, activeCube.getZ() - zOffset);
                }
            }
        }
    }
    
    private void processCube(int x, int y, int z) {
        Optional<Cube> optionalCube = getCubeFromAdjacentList(x, y, z);
        if (optionalCube.isPresent()) {
            optionalCube.get().addActivation();
        } else {
            optionalCube = getCubeFromActiveList(x, y, z);
            if (optionalCube.isPresent()) {
                optionalCube.get().addActivation();
            } else {
                adjacentCubes.add(new Cube(x, y, z, false, 1));
            }
        }
    }
    
    private void part2() {
    
    }
    
    private Optional<Cube> getCubeFromAdjacentList(int x, int y, int z) {
        return adjacentCubes.stream()
                .filter(cube -> cube.getX() == x)
                .filter(cube -> cube.getY() == y)
                .filter(cube -> cube.getZ() == z)
                .findFirst();
    }
    
    private Optional<Cube> getCubeFromActiveList(int x, int y, int z) {
        return adjacentCubes.stream()
                .filter(cube -> cube.getX() == x)
                .filter(cube -> cube.getY() == y)
                .filter(cube -> cube.getZ() == z)
                .findFirst();
    }
    
    public static void main(String[] args) {
        ExecutionTime.start();
        new Puzzle17();
        ExecutionTime.stop();
    }
}
