package year_2019.day06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static year_2019.day06.SolarSystemFactory.readInSolarSystem;

public class Day6 implements IDay6 {

    public final Map<String, Planet> map;

    Day6(String fileName) throws FileNotFoundException {
        map = readInSolarSystem(fileName);
    }

    @Override
    public int getOrbitalChecksum() {
        return map.get("COM").orbitalChecksum();
    }

    @Override
    public int getDistanceToSanta() {
        Planet YOU = map.get("YOU");
        Planet SANTA = map.get("SAN");

        /*
        You start at the object you are orbiting, so you don't need to "move" to that one
        and Santa is on the object he is orbiting, so you don't need to move to that one either.
         */
        return BFSUtil.doBFS(YOU, SANTA, Planet::getNeighbors).intValue() - 2;
    }
}
