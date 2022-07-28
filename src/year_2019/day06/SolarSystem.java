package year_2019.day06;

import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class SolarSystem implements ISolarSystem {
    public final Map<String, Planet> solarSystem;

    @Override
    public Planet getPlanet(String name) {
        return solarSystem.get(name);
    }
}