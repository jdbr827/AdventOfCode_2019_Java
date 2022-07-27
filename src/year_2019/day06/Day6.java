package year_2019.day06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day6 {

    public static Map<String, Planet> map = new HashMap<>();

    public static void main(String[] args) throws FileNotFoundException {
        readIn();
        System.out.println(map.get("COM").getTotalOrbitTotal() == 621125);

        /* Part 2 */
        Collection<Planet> planets = map.values();

        Planet YOU = map.get("YOU");
        Planet SANTA = map.get("SAN");


        Map<Planet, Integer> distanceFromYou = new HashMap<>();
        for (Planet p : planets) {
            distanceFromYou.put(p, Integer.MAX_VALUE);
        }

        Queue<Planet> old_bfs = new LinkedList<>();
        old_bfs.add(YOU);
        distanceFromYou.put(YOU, 0);

        while (distanceFromYou.get(SANTA) == Integer.MAX_VALUE) {
            Planet thisPlanet = old_bfs.remove();
            for (Planet neighbor: thisPlanet.getNeighbors()) {
                if (distanceFromYou.get(neighbor) == Integer.MAX_VALUE) {
                    distanceFromYou.put(neighbor, distanceFromYou.get(thisPlanet) + 1);
                    old_bfs.add(neighbor);
                }
            }
        }

        /*
        You start at the object you are orbiting, so you don't need to "move" to that one
        and Santa is on the object he is orbiting, so you don't need to move to that one either.
         */
        System.out.println(distanceFromYou.get(SANTA) - 2 == 550);
    }

    private static void readIn() throws FileNotFoundException {
        String INPUT_FILENAME = "src/year_2019/day06/input_aoc_2019_6.txt";
        File file = new File(INPUT_FILENAME);
        Scanner scanner = new Scanner(file);

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
    }

}