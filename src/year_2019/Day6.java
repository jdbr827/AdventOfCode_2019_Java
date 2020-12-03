package year_2019;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day6 {

    public static Map<String, Planet> map = new HashMap<>();

    static class Planet {

        List<Planet> orbiters = new ArrayList<>();
        Planet parent = null;

        Planet() {
        }

        /**
         * The total number of planets orbiting this planet
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
         * @return the number of orbits (direct or indirect) between two planets
         */
        public int getTotalOrbitTotal() {
            return orbiters.isEmpty()
                    ? 0
                    : orbiters.stream().mapToInt(Planet::getTotalOrbitTotal).sum() +
                        orbiters.stream().mapToInt(Planet::getOrbitTotal).sum() + orbiters.size();
        }

        public void addOrbiter(Planet p) {
            orbiters.add(p);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String INPUT_FILENAME = "C:\\Users\\Jake\\IdeaProjects\\AdventOfCode_2020\\src\\year_2019\\input_aoc_2019_6.txt";
        File file = new File(INPUT_FILENAME);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String[] data = scanner.nextLine().split("\\)");
            map.putIfAbsent(data[0], new Planet());
            map.putIfAbsent(data[1], new Planet());
            map.get(data[0]).addOrbiter(map.get(data[1]));
            map.get(data[1]).parent = map.get(data[0]);
        }


        System.out.println(map.get("COM").getTotalOrbitTotal() == 621125);

        /* Part 2 */
        Collection<Planet> planets = map.values();


        Map<Planet, Integer> distanceFromYou = new HashMap<>();
        for (Planet p : planets) {
            distanceFromYou.put(p, Integer.MAX_VALUE);
        }

        Queue<Planet> old_bfs = new LinkedList<>();
        old_bfs.add(map.get("YOU"));
        distanceFromYou.put(map.get("YOU"), 0);

        while (distanceFromYou.get(map.get("SAN")) == Integer.MAX_VALUE) {
            Planet thisPlanet = old_bfs.remove();
            for (Planet orbiter : thisPlanet.orbiters) {
                if (distanceFromYou.get(orbiter) == Integer.MAX_VALUE) {
                    distanceFromYou.put(orbiter, distanceFromYou.get(thisPlanet) + 1);
                    old_bfs.add(orbiter);
                }
            }
            if (thisPlanet.parent != null) {
                if (distanceFromYou.get(thisPlanet.parent) == Integer.MAX_VALUE) {
                    distanceFromYou.put(thisPlanet.parent, distanceFromYou.get(thisPlanet) + 1);
                    old_bfs.add(thisPlanet.parent);
                }
            }
        }

        /*
        You start at the object you are orbiting so you don't need to "move" to that one
        and Santa is on the object he is orbiting so you don't need to move to that one either.
         */
        System.out.println(distanceFromYou.get(map.get("SAN")) - 2 == 550);
    }

}
