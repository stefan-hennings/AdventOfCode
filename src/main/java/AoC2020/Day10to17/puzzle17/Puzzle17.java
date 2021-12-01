package AoC2020.Day10to17.puzzle17;

import utility.ExecutionTime;
import utility.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Puzzle17 {
    String test = "test";
    String real = "AoC2020.Day10to17\\puzzle17";
    
    List<String> startingSlice;
    
    List<Cube> activeCubes;
    List<Cube> inactiveCubes;
    
    List<Cube4D> activeCubes4D;
    List<Cube4D> inactiveCubes4D;
    
    public Puzzle17() {
        startingSlice = Utility.readStringFile(real);
        
        part1();
        
        part2();
    }
    
    private void part1() {
        activeCubes = new ArrayList<>();
        for (int y = 0; y < startingSlice.size(); y++) {
            for (int x = 0; x < startingSlice.get(y).length(); x++) {
                if (startingSlice.get(y).charAt(x) == '#') {
                    activeCubes.add(new Cube(x, y, 0));
                }
            }
        }
        
        for (int i = 0; i < 6; i++) {
            inactiveCubes = new ArrayList<>();
            for (Cube activeCube : activeCubes) {
                processAdjacentCubes(activeCube);
            }

            activeCubes.removeIf(cube -> cube.getAdjacentActiveCubes() != 2 && cube.getAdjacentActiveCubes() != 3);

            for (Cube cube : inactiveCubes) {
                if (cube.getAdjacentActiveCubes() == 3) {
                    activeCubes.add(cube);
                }
            }
            
            for (Cube activeCube : activeCubes) {
                activeCube.setAdjacentActiveCubes(0);
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
        //If already present in the inactive list
        Optional<Cube> optionalCube = getInactiveCube(x, y, z);
        if (optionalCube.isPresent()) {
            optionalCube.get().incrementAdjacentActiveCubes();
            return;
        }
    
        Optional<Cube> found = Optional.empty();
        for (Cube cube : activeCubes) {
            if (cube.getX() == x && cube.getY() == y && cube.getZ() == z) {
                found = Optional.of(cube);
                break;
            }
        }
        found.ifPresentOrElse(Cube::incrementAdjacentActiveCubes, () -> inactiveCubes.add(new Cube(x, y, z, 1)));
    }
    
    private Optional<Cube> getInactiveCube(int x, int y, int z) {
        for (Cube cube : inactiveCubes) {
            if (cube.getX() == x && cube.getY() == y && cube.getZ() == z) {
                return Optional.of(cube);
            }
        }
        return Optional.empty();
    }
    
    private void part2() {
        activeCubes4D = new ArrayList<>();
        for (int y = 0; y < startingSlice.size(); y++) {
            for (int x = 0; x < startingSlice.get(y).length(); x++) {
                if (startingSlice.get(y).charAt(x) == '#') {
                    activeCubes4D.add(new Cube4D(x, y, 0, 0));
                }
            }
        }
        System.out.println(activeCubes4D.size());
    
        for (int i = 0; i < 6; i++) {
            inactiveCubes4D = new ArrayList<>();
            for (Cube4D activeCube : activeCubes4D) {
                processAdjacentCubes(activeCube);
            }

            activeCubes4D.removeIf(cube -> cube.getAdjacentActiveCubes() != 2 && cube.getAdjacentActiveCubes() != 3); //reuses 3D cube getters
            
            for (Cube4D cube : inactiveCubes4D) {
                if (cube.getAdjacentActiveCubes() == 3) {
                    activeCubes4D.add(cube);
                }
            }
        
            for (Cube4D activeCube : activeCubes4D) {
                activeCube.setAdjacentActiveCubes(0);
            }
            System.out.println("Complete iterations: " + (i + 1));
            System.out.println(activeCubes4D.size());
        }

        System.out.println(activeCubes4D.size());
    }
    
    private void processAdjacentCubes(Cube4D activeCube) {
        for (int wOffset = -1; wOffset <= 1; wOffset++) {
            for (int zOffset = -1; zOffset <= 1; zOffset++) {
                for (int yOffset = -1; yOffset <= 1; yOffset++) {
                    for (int xOffset = -1; xOffset <= 1; xOffset++) {
                        if (xOffset == 0 && yOffset == 0 && zOffset == 0 && wOffset == 0) {
                            continue;
                        }
                        processCube4D(activeCube.getX() - xOffset, activeCube.getY() - yOffset, activeCube.getZ() - zOffset, activeCube.getW() - wOffset);
                    }
                }
            }
        }
    }
    
    private void processCube4D(int x, int y, int z, int w) {
        //If already present in the inactive list
        Optional<Cube4D> optionalCube = getInactiveCube4D(x, y, z, w);
        if (optionalCube.isPresent()) {
            optionalCube.get().incrementAdjacentActiveCubes();
            return;
        }
    
        Optional<Cube4D> found = Optional.empty();
        for (Cube4D cube : activeCubes4D) {
            if (cube.getX() == x && cube.getY() == y && cube.getZ() == z && cube.getW() == w) {
                found = Optional.of(cube);
                break;
            }
        }
        found.ifPresentOrElse(Cube::incrementAdjacentActiveCubes, () -> inactiveCubes4D.add(new Cube4D(x, y, z, w, false, 1)));
    }
    
    private Optional<Cube4D> getInactiveCube4D(int x, int y, int z, int w) {
        for (Cube4D cube : inactiveCubes4D) {
            if (cube.getX() == x && cube.getY() == y && cube.getZ() == z && cube.getW() == w) {
                return Optional.of(cube);
            }
        }
        return Optional.empty();
    }
    
    public static void main(String[] args) {
        ExecutionTime.start();
        new Puzzle17();
        ExecutionTime.stop();
    }
}
