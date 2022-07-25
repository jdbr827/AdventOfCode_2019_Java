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
    public int getOrbitTotal() {
        int total = 0;
        for (Planet orbiter : orbiters) {
            total += orbiter.getOrbitTotal(); // every planet orbiting orbiter indirectly orbits this planet
            total += 1; // orbiter directly orbits this planet
        }
        return total;
    }

    /**
     * Returns the number of orbits of Planet or any planet in direct or indirect orbit of Planet
     * |{(a, b) st. (aOthis || a == this) && (bOthis || b==this) && aOb}|
     * @return the number of orbits (direct or indirect) between two planets
     */
    public int getTotalOrbitTotal() {
        return orbiters.isEmpty() ? 0
                : orbiters.stream().mapToInt(Planet::getTotalOrbitTotal).sum()
                    + orbiters.stream().mapToInt(Planet::getOrbitTotal).sum() + orbiters.size();
    }

    public void addOrbiter(Planet p) {
        orbiters.add(p);
    }
}
