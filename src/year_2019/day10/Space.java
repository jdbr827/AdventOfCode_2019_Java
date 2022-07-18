package year_2019.day10;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Streams;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.math.IntMath.gcd;

class Space {
    List<Point> asteroids;

    public Point findIthVaporizedFromStation(Point station, int i) {
        asteroids.remove(station);

        Map<Point, List<Point>> myMap = asteroids.stream()
                .collect(Collectors.groupingBy((a) -> reduceVectorByGCD(rewriteVectorAtNewOrigin(a, station))));

        //System.out.println(myMap.values().stream().filter(lst -> lst.get(0).getX() == 11).collect(Collectors.toList()));


        myMap.forEach((k, ptLst) -> ptLst.sort(Comparator.comparing((a) -> gcdOfComponents(rewriteVectorAtNewOrigin(a, station)))));
        List<Point> sortedByAngles = myMap.keySet().stream()
                .sorted(Comparator.comparing(Space::getClockwiseArcTanFromTop))
                .collect(Collectors.toList());

        System.out.println(myMap.get(sortedByAngles.get(0)).get(0));
        System.out.println(myMap.get(sortedByAngles.get(1)).get(0));

        List<Point> orderedPoints = new ArrayList<>();

        int j=0;
        do {
            System.out.println(sortedByAngles.size());
            int finalJ = j;
            orderedPoints.addAll(sortedByAngles.stream().map(v -> myMap.get(v).get(finalJ)).collect(Collectors.toList()));
            sortedByAngles = sortedByAngles.stream().filter(v -> (myMap.get(v).size() >= finalJ + 2)).collect(Collectors.toList());
            j++;
        }
        while (!sortedByAngles.isEmpty());





        return orderedPoints.get(i-1);
    }


    public static double getClockwiseArcTanFromTop(Point pt) {
        double atan = Math.atan2(pt.x, -pt.y);
        return atan >= 0 ? atan : atan + (2*Math.PI);
    }

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
