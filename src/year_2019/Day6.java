package year_2019;

import guru.nidi.graphviz.attribute.Font;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.model.Graph;

import javax.swing.tree.TreeNode;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

import static guru.nidi.graphviz.attribute.Rank.RankDir.LEFT_TO_RIGHT;
import static guru.nidi.graphviz.model.Factory.graph;

public class Day6 {


    public static Map<String, Planet> map = new HashMap<>();

    static class Planet {

        List<Planet> orbiters = new ArrayList<>();
        Planet parent = null;

        Planet() {
        }

        /*
        If a planet has nothing orbiting it, 0
        Otherwise, each planet directly orbiting a planet directly orbiting this planet is indirectly orbiting this planet
        and all planets directly orbiting this planet need to count too
         */

        /**
         * Returns the number of planets in direct or indirect orbit of Planet
         * @return
         */
        public int getOrbitTotal() {
            return orbiters.isEmpty()
                    ? 0
                    : orbiters.stream().mapToInt(Planet::getOrbitTotal).sum() + orbiters.size();
        }

        /**
         * Returns the number of orbits of Planet or any planet in direct or indirect orbit of Planet
         * @return
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



//        System.out.println(map.get("K").getTotalOrbitTotal());
//        System.out.println(map.get("J").getTotalOrbitTotal());
        System.out.println(map.get("COM").getTotalOrbitTotal());


    }

}
