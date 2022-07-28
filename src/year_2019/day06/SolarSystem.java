package year_2019.day06;

import lombok.AllArgsConstructor;

import java.util.Collections;
import java.util.Map;

@AllArgsConstructor
class SolarSystem {

    private final Map<String, Planet> solarSystem;

    public static SolarSystem create(Map<String, Planet> solarSystem) {
        return new SolarSystem(Collections.unmodifiableMap(solarSystem));
    }

    public Planet getPlanet(String name) {
        return solarSystem.get(name);
    }
}
