package year_2019.day12;

import javafx.util.Pair;
import static utils.MathUtils.lcm;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Day12 {
    private static final int X_DIRECTION = 0;
    private static final int Y_DIRECTION = 1;
    private static final int Z_DIRECTION = 2;
    public static final int[] AXES = {X_DIRECTION, Y_DIRECTION, Z_DIRECTION};

    static int part1(String inputFile, int steps) throws IOException {
        SolarSystem solarSystem = new SolarSystem(inputFile);
        solarSystem.executeNTimeSteps(steps);
        return solarSystem.calculateTotalEnergy();
    }

    static BigInteger part2(String inputFile) throws IOException {
        SolarSystem solarSystem = new SolarSystem(inputFile);
        return solarSystem.findMinutesUntilFirstRepeat();
    }
}

class Moon {
    int[] position;
    int[] velocity = {0, 0, 0};

    Moon(int[] initialPosition) {
        this.position = initialPosition;
    }

    public void applyGravityFromInDirection(Moon otherMoon, int direction) {
        if (otherMoon.position[direction] < this.position[direction]) {
                velocity[direction] -= 1;
            } else if (otherMoon.position[direction] > this.position[direction]) {
                velocity[direction] += 1;
            }
    }

    public void applyVelocityInDirection(int direction) {
        position[direction] += velocity[direction];
    }

    public int calculateTotalEnergy() {
        return calculatePotentialEnergy() * calculateKineticEnergy();
    }

    private int calculateKineticEnergy() {
        return Arrays.stream(velocity)
                .map(Math::abs)
                .sum();

    }

    private int calculatePotentialEnergy() {
        return Arrays.stream(position)
                .map(Math::abs)
                .sum();
    }

}

class SolarSystem {
    List<Moon> moons;

    public SolarSystem(String s) throws IOException {
        this.moons = new MoonReader(s).readInMoons();
    }

    public BigInteger findMinutesUntilFirstRepeat() {
        int[] periods = findPeriodsInEachDirection();
        return lcm(periods);
    }

    private int[] findPeriodsInEachDirection() {
        return Arrays.stream(Day12.AXES).map(this::findPeriodInDirection).toArray();
    }



    /*
    Because executing a time step is invertible,
    we know that the first repeated state will always be the initial state.
     */
    private int findPeriodInDirection(int direction) {
        List<Pair<Integer, Integer>> originalState = getStateInDirection(direction);
        List<Pair<Integer, Integer>> currentState = executeTimeStepAndGetStateInDirection(direction);

        int minutes = 1;
        while (!(currentState.equals(originalState))) {
            currentState = executeTimeStepAndGetStateInDirection(direction);
            minutes++;
        }
        return minutes;
    }

    private List<Pair<Integer, Integer>> getStateInDirection(int direction) {
        return moons.stream()
                .map((Moon moon) -> new Pair<>(moon.position[direction], moon.velocity[direction]))
                .collect(Collectors.toList());
    }

    private List<Pair<Integer, Integer>> executeTimeStepAndGetStateInDirection(int direction) {
        executeTimeStepInDirection(direction);
        return getStateInDirection(direction);
    }

    public int calculateTotalEnergy() {
        return moons.stream()
                .mapToInt(Moon::calculateTotalEnergy)
                .sum();
    }

    public void executeNTimeSteps(int n) {
        for (int i=0; i<n; i++) {executeTimeStep();}
    }

    private void executeTimeStep() {
       Arrays.stream(Day12.AXES).forEach(this::executeTimeStepInDirection);
    }

    private void executeTimeStepInDirection(int direction) {
        applyGravityInDirection(direction);
        applyVelocityInDirection(direction);
    }

    private void applyVelocityInDirection(int direction) {
        moons.forEach((Moon moon) -> moon.applyVelocityInDirection(direction));
    }

    private void applyGravityInDirection(int direction) {
        for (Moon moon1 : moons) {
            for (Moon moon2: moons) {
                moon1.applyGravityFromInDirection(moon2, direction);
            }
        }
    }




}
