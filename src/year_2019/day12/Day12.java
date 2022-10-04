package year_2019.day12;

import com.google.common.collect.ImmutableList;
import javafx.util.Pair;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

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
    MoonDimension[] moonDirections = new MoonDimension[3];

    Moon(int[] initialPosition) {
        moonDirections[0] = new MoonDimension(initialPosition[0]);
        moonDirections[1] = new MoonDimension(initialPosition[1]);
        moonDirections[2] = new MoonDimension(initialPosition[2]);
    }

    public int calculateTotalEnergy() {
        return calculatePotentialEnergy() * calculateKineticEnergy();
    }

    private int calculateKineticEnergy() {
        return Arrays.stream(moonDirections)
                .map(dir -> dir.velocity)
                .mapToInt(Math::abs)
                .sum();

    }

    private int calculatePotentialEnergy() {
        return Arrays.stream(moonDirections)
                .map(dir -> dir.position)
                .mapToInt(Math::abs)
                .sum();
    }

}


class MoonState extends Pair<Integer, Integer> {
    public MoonState(Integer key, Integer value) {
        super(key, value);
    }
}

@AllArgsConstructor
@RequiredArgsConstructor
class MoonDimension {
    @NotNull Integer position;
    Integer velocity = 0;

    public void applyGravityFrom(MoonDimension otherMoonDirection) {
        if (otherMoonDirection.position < this.position) {
            velocity -= 1;
        } else if (otherMoonDirection.position > this.position) {
            velocity += 1;
        }
    }

    public void applyVelocity() {
        position += velocity;
    }

    public MoonDimension copy() {
        return new MoonDimension(position, velocity);
    }

    public static List<MoonDimension> copyList(List<MoonDimension> moonDimensionsList) {
        return moonDimensionsList.stream().map(MoonDimension::copy).collect(Collectors.toList());
    }

    public boolean inSameStateAs(MoonDimension moon2) {
        return position.equals(moon2.position) && velocity.equals(moon2.velocity);
    }
}

class SolarSystem {
    ImmutableList<Moon> moons;
    SolarSystemDimension[] solarSystemDimensions = new SolarSystemDimension[3];

    public SolarSystem(String s) throws IOException {
        this.moons = new MoonReader(s).readInMoons();
        this.solarSystemDimensions[0] = new SolarSystemDimension(ImmutableList.copyOf(moons.stream().map(moon -> moon.moonDirections[0]).collect(Collectors.toList())));
        this.solarSystemDimensions[1] = new SolarSystemDimension(ImmutableList.copyOf(moons.stream().map(moon -> moon.moonDirections[1]).collect(Collectors.toList())));
        this.solarSystemDimensions[2] = new SolarSystemDimension(ImmutableList.copyOf(moons.stream().map(moon -> moon.moonDirections[2]).collect(Collectors.toList())));
    }

    public BigInteger findMinutesUntilFirstRepeat() {
        int[] periods = findPeriodsInEachDirection();
        return lcm(periods);
    }

    private int[] findPeriodsInEachDirection() {
        return Arrays.stream(solarSystemDimensions).mapToInt(SolarSystemDimension::findPeriod).toArray();
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
       Arrays.stream(solarSystemDimensions).forEach(SolarSystemDimension::executeTimeStep);
    }
}

@AllArgsConstructor
class SolarSystemDimension {
    ImmutableList<MoonDimension> moonDimensions;

    public void applyVelocity() {
        moonDimensions.forEach(MoonDimension::applyVelocity);
    }

    public void applyGravity() {
        for (MoonDimension moon1: moonDimensions) {
            for (MoonDimension moon2 : moonDimensions) {
                moon1.applyGravityFrom(moon2);
            }
        }
    }

    public void executeTimeStep() {
        applyGravity();
        applyVelocity();
    }

    public boolean inSameStateAs(List<MoonDimension> solarSystem2) {
        if (solarSystem2.size() != moonDimensions.size()) { return false; }
        for (int i=0; i<moonDimensions.size(); i++) {
            if (!moonDimensions.get(i).inSameStateAs(solarSystem2.get(i))) {
                return false;
            }
        }
        return true;
    }




     /*
    Because executing a time step is invertible,
    we know that the first repeated state will always be the initial state.
     */
    public int findPeriod() {
        List<MoonDimension> originalState = MoonDimension.copyList(moonDimensions); // copy
        executeTimeStep();

        int minutes = 1;
        while (!(inSameStateAs(originalState))) {
            executeTimeStep();
            minutes++;
        }
        return minutes;

    }





}
