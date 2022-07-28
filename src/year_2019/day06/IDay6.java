package year_2019.day06;

import java.io.FileNotFoundException;

import static year_2019.day06.SolarSystemFactory.readInSolarSystem;

public interface IDay6 {

    int getOrbitalChecksum();

    int getDistanceToSanta();

    static IDay6 create(String fileName) throws FileNotFoundException {
        return new Day6(readInSolarSystem(fileName));
    }
}
