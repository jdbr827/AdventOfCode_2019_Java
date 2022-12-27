package year_2022.day_15;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Day15 {

    public static int manhattanDistance(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

    public static int part1(String fileName, int Y) throws FileNotFoundException {
        Day15Scanner scanner = new Day15Scanner(fileName);
        Map<Point, Point> beacons = scanner.readInSensorInfo();
        Map<Integer, Integer> hasBeacon = new HashMap<>(); // 0 = false, 1= true, 2 = unknown
        int count = 0;

        for (Point beacon : beacons.values()) {
            if (beacon.getY() == Y) {
                hasBeacon.put(beacon.x, 1);
            }
        }

        for (Point sensor : beacons.keySet()) {
            int m = manhattanDistance(sensor, beacons.get(sensor));
            int xAvailable = m - Math.abs(sensor.y - Y);
            for (int ruledOutX=sensor.x-xAvailable; ruledOutX <= sensor.x + xAvailable; ruledOutX++) {
                if (hasBeacon.getOrDefault(ruledOutX, 2) == 2) {
                    hasBeacon.put(ruledOutX, 0);
                    count++;
                }
            }
        }
        return count;
    }


    public static long part2(String fileName, int nMax) throws FileNotFoundException {
        Day15Scanner scanner = new Day15Scanner(fileName);
        Map<Point, Point> beaconMap = scanner.readInSensorInfo();
        int count = 0;

        Map<Point, Integer> distances = beaconMap.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, (e) -> manhattanDistance(e.getKey(), e.getValue())));

        Collection<Point> sensors = beaconMap.keySet();
        Collection<Point> beacons = beaconMap.values();

        for (int y = 0; y<= nMax; y++) {
            for (int x=0; x<= nMax; x++) {
                Point p = new Point(x, y);
                //System.out.println(p);
                if (!beacons.contains(p)) {
                    boolean foundFlag = true;
                    for (Point s : sensors) {
                        if (manhattanDistance(s, p) <= distances.get(s)) {
                            x += distances.get(s) - Math.abs(s.y - y) + s.x - x;
                            foundFlag = false;
                            break;
                        }
                    }
                    if (foundFlag) {
                        System.out.println(x + " " + y);
                        return (long) x * 4000000 + y;
                    }
                }
            }
        }
        return 0;
    }
}
