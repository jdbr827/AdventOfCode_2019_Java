package year_2019.day06;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



/*
The direct orbit relation aDb ("a directly orbits b").
The orbit relation a0b ("a orbits b") is the transitive closure of aDb.
for any planet a except COM, there is a unique planet b such that aDb ("a directly orbits b"). Call such b the "parent" of a.
 */


@NoArgsConstructor
class Planet {

    @Getter private final List<Planet> orbiters = new ArrayList<>(); // The set of planets in direct orbit of this planet.
    @Getter @Setter private Planet parent = null; // The planet which this planet is orbiting, null for COM

    /**
     * Returns the set of all neighbors (orbiters and parent if any)
     * @return
     */
    public Collection<Planet> getNeighbors() {
        List<Planet> neighbors = getOrbiters();
        if (getParent() != null) {
            neighbors.add(parent);
        }
        return neighbors;
    }


    /**
     * The number of planets p such that p orbits this. The total number of planets orbiting this planet.
     * |{b: bOthis}|
     *
     * @return the number of planets in orbit (direct or indirect) of this planet
     */
    public int numOrbitsOfThisPlanet() {
        return numDirectOrbitsOfThisPlanet() + numIndirectOrbitsOfThisPlanet();
    }

    /**
     * Returns the number of orbits of Planet or any planet in direct or indirect orbit of Planet
     * |{(a, b) st. (aOthis || a == this) && (bOthis || b==this) && aOb}|
     * @return the number of orbits (direct or indirect) between two planets
     */
    public int orbitalChecksum() {

        return orbiters.isEmpty() ? 0
                : numOrbitsOfPlanetsThatOrbitThisPlanet()
                    + numIndirectOrbitsOfThisPlanet()
                    + numDirectOrbitsOfThisPlanet();
    }

    private int numOrbitsOfPlanetsThatOrbitThisPlanet() {
        return orbiters.stream().mapToInt(Planet::orbitalChecksum).sum();
    }

    private int numDirectOrbitsOfThisPlanet() {
        return orbiters.size();
    }

    private int numIndirectOrbitsOfThisPlanet() {
        //
        return orbiters.stream()
                .mapToInt(Planet::numOrbitsOfThisPlanet)
                .sum();
    }

    public void addOrbiter(Planet p) {
        orbiters.add(p);
    }
}
