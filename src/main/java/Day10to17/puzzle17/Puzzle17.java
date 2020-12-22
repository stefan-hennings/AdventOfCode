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
    List<Cube> inactiveCubes;
    
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
                    activeCubes.add(new Cube(x, y, 0, true));
                }
            }
        }
        
        activeCubes.forEach(System.out::println);
        System.out.println();
        
        for (int i = 0; i < 6; i++) {
            inactiveCubes = new ArrayList<>();
            for (Cube activeCube : activeCubes) {
                processAdjacentCubes(activeCube);
            }
//            System.out.println("Active cubes before removal: ");
//            activeCubes.forEach(System.out::println);
            
            activeCubes.removeIf(cube -> cube.getAdjacentActiveCubes() != 2 && cube.getAdjacentActiveCubes() != 3);
            
//            System.out.println("Active cubes after removal: ");
//            activeCubes.forEach(System.out::println);
            
            
            for (Cube cube : inactiveCubes) {
                if (cube.getAdjacentActiveCubes() == 3) {
                    cube.setActive(true);
                    activeCubes.add(cube);
                }
            }
            activeCubes.forEach(System.out::println);


            for (Cube activeCube : activeCubes) {
                activeCube.setAdjacentActiveCubes(0);
            }
        }

//        activeCubes.forEach(System.out::println);
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
    
        activeCubes.stream()
                .filter(cube -> cube.getX() == x)
                .filter(cube -> cube.getY() == y)
                .filter(cube -> cube.getZ() == z)
                .findFirst()
                .ifPresentOrElse(Cube::incrementAdjacentActiveCubes, () -> inactiveCubes.add(new Cube(x, y, z, false, 1)));
        /*optionalCube = getActiveCube(x, y, z);
        if (optionalCube.isPresent()) {
            optionalCube.get().addActivation();
            activeCubes.get(activeCubes.indexOf(optionalCube.get())).addActivation();
        } else {
            inactiveCubes.add(new Cube(x, y, z, false, 1));
        }*/
    }
    
    private void part2() {
    
    }
    
    private Optional<Cube> getInactiveCube(int x, int y, int z) {
        return inactiveCubes.stream()
                .filter(cube -> cube.getX() == x)
                .filter(cube -> cube.getY() == y)
                .filter(cube -> cube.getZ() == z)
                .findFirst();
    }
    
    private Optional<Cube> getActiveCube(int x, int y, int z) {
        return inactiveCubes.stream()
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
