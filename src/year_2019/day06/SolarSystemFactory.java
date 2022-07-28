package year_2019.day06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SolarSystemFactory {

    public static SolarSystem readInSolarSystem(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        Map<String, Planet> map = new HashMap<>();

        while (scanner.hasNextLine()) {
            String[] data = scanner.nextLine().split("\\)");

            String orbitingPlanetName = data[0];
            String orbitedPlanetName = data[1];

            map.putIfAbsent(orbitingPlanetName, new Planet());
            map.putIfAbsent(orbitedPlanetName, new Planet());

            Planet orbitingPlanet = map.get(orbitingPlanetName);
            Planet orbitedPlanet = map.get(orbitedPlanetName);

            orbitingPlanet.addOrbiter(orbitedPlanet);
            orbitedPlanet.setParent(orbitingPlanet);
        }
        return SolarSystem.create(map);
    }
};
