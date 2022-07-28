package year_2019.day06;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



@NoArgsConstructor
class Planet {

    @Getter private final List<Planet> orbiters = new ArrayList<>(); // The set of planets in direct orbit of this planet.
    @Getter @Setter private Planet parent = null; // The planet which this planet is orbiting, null for COM


    public Collection<Planet> getNeighbors() {
        List<Planet> neighbors = getOrbiters();
        if (getParent() != null) {
            neighbors.add(parent);
        }
        return neighbors;
    }



    public int numOrbitsOfThisPlanet() {
        return numDirectOrbitsOfThisPlanet() + numIndirectOrbitsOfThisPlanet();
    }

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
        return orbiters.stream()
                .mapToInt(Planet::numOrbitsOfThisPlanet)
                .sum();
    }

    public void addOrbiter(Planet p) {
        orbiters.add(p);
    }
}
