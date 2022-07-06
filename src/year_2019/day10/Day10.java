package year_2019.day10;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;

import static com.google.common.math.IntMath.gcd;


public class Day10 {

    public static int part1(String fileName) throws IOException {
        Space space = new Space(fileName);
        return space.findMostVisibleNeighbors();
    }

    public static Point findBestStation(String fileName) throws IOException {
        Space space = new Space(fileName);
        return space.findBestMonitoringStation();
    }
}


class Space {
    List<Point> asteroids;

    Space(String fileName) throws IOException {
        asteroids = readInSpace(fileName);
    }


    public static List<Point> readInSpace(String fileName) throws IOException {
        List<Point> space = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        int x = 0;
        while ((line = br.readLine()) != null) {
            for (int y=0; y<line.length(); y++) {
                if (line.charAt(y) == '#') {
                    space.add(new Point(y, x));
                }
            }
            x++;
        }
        return space;
    }


    int numVisibleNeighbors(Point point) {
        Set<Point> reducedVectors = getVisibleNeighbors(point);
        System.out.println(reducedVectors);
        return reducedVectors.size() - 1; // since (0, 0) is the point itself

    }

    Set<Point> getVisibleNeighbors(Point point) {
        List<Point> vectors = new ArrayList<>();
        for (Point p: asteroids) {
            vectors.add(new Point(p.x - point.x, p.y - point.y));
        }
        Set<Point> reducedVectors = new HashSet<>();
        for (Point v: vectors) {
            int vGcd;
            if (v.x == 0 && v.y == 0) {
                vGcd = 1;
            } else if (v.x == 0) {
                vGcd = Math.abs(v.y);
            } else if (v.y == 0) {
                vGcd = Math.abs(v.x);
            } else {
                vGcd = gcd(Math.abs(v.x), Math.abs(v.y));
            }

            reducedVectors.add(new Point(v.x / vGcd, v.y / vGcd));
        }
        return reducedVectors;
    }

    public int findMostVisibleNeighbors() {
        return asteroids.stream().map(this::numVisibleNeighbors).max(Comparator.naturalOrder()).orElse(-1);
    }

    public Point findBestMonitoringStation() {
        return asteroids.stream().max(Comparator.comparing(this::numVisibleNeighbors)).orElse(null);
    }


}