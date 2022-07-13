package year_2019.day10;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.math.IntMath.gcd;

class Space {
    List<Point> asteroids;

    Space(String fileName) throws IOException {
        asteroids = Day10FileReader.readInSpace(fileName);
    }


    int numVisibleNeighbors(Point point) {
        Set<Point> reducedVectors = getVisibleNeighbors(point);
        return reducedVectors.size() - 1; // since (0, 0) is the point itself

    }

    Set<Point> getVisibleNeighbors(Point point) {
        return asteroids.stream()
                .map((Point p) -> rewriteVectorAtNewOrigin(p, point))
                .map(Space::reduceVectorByGCD)
                .collect(Collectors.toSet()); // Important to remove duplicates
    }

    private static Point rewriteVectorAtNewOrigin(Point vector, Point newOrigin) {
        return new Point(vector.x - newOrigin.x, vector.y - newOrigin.y);
    }

    /**
     * Given a vector v, reduces vector components by dividing each component by their GCDS
     * @param v a 2d point
     * @return a vector in the same direction as v but with magnitude reduced by the GCD of v's components
     */
    private static Point reduceVectorByGCD(Point v) {
        int vGcd = gcdOfComponents(v);
        return new Point(v.x / vGcd, v.y / vGcd);
    }


    private static int gcdOfComponents(Point v) {
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
        return vGcd;
    }

    public int findMostVisibleNeighbors() {
        return asteroids.stream()
                .map(this::numVisibleNeighbors)
                .max(Comparator.naturalOrder())
                .orElse(-1);
    }

    public Point findBestMonitoringStation() {
        return asteroids.stream()
                .max(Comparator.comparing(this::numVisibleNeighbors))
                .orElse(null);
    }


}
