package year_2019.day06;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Day6 implements IDay6 {
    public final ISolarSystem solarSystem;

    @Override
    public int getOrbitalChecksum() {
        return solarSystem.getPlanet("COM").orbitalChecksum();
    }

    @Override
    public int getDistanceToSanta() {
        Planet YOU = solarSystem.getPlanet("YOU");
        Planet SANTA = solarSystem.getPlanet("SAN");

        /*
        You start at the object you are orbiting, so you don't need to "move" to that one
        and Santa is on the object he is orbiting, so you don't need to move to that one either.
         */
        return BFSUtil.doBFS(YOU, SANTA, Planet::getNeighbors).intValue() - 2;
    }
}
