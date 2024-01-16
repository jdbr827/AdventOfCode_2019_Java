package year_2023.day_22;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import utils.AOCScanner;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day22 {
    private static final Brick GROUND = new Brick(List.of(0, 0, 0), List.of(Integer.MAX_VALUE, Integer.MAX_VALUE, 0));
    Collection<Brick> bricks = new ArrayList<>();
     int xMax;
     int yMax;

    public Day22(String fileName) {
        AOCScanner scanner = new AOCScanner(fileName);
        scanner.forEachLine(line -> {
            bricks.add(Brick.scanIn(line));
        });
        xMax = bricks.stream().map(brick -> brick.x2).max(Comparator.naturalOrder()).get();
        yMax = bricks.stream().map(brick -> brick.y2).max(Comparator.naturalOrder()).get();

    }

    public int getNumToSafelyDisintegrate() {
        simulateBrickFalling();
        return countNumToSafelyDisintegrate();
    }

    private int countNumToSafelyDisintegrate() {
        return (int) bricks.stream().filter(Brick::isSafeToDisintegrate).count();
    }

    private void simulateBrickFalling() {
        Brick[][] highestSettled = new Brick[xMax+1][yMax+1];
        for (int x=0; x<xMax+1; x++) {
            for (int y=0; y<yMax+1; y++) {
                highestSettled[x][y] = GROUND;
            }
        }
        List<Brick> unsettledBricks = bricks.stream().sorted(Comparator.comparing(Brick::lowestZ)).collect(Collectors.toList());
        while (!unsettledBricks.isEmpty()) {
            for (Brick brick : unsettledBricks) {
                brick.getCrossSection().forEach(pt -> {
                    if (highestSettled[pt.x][pt.y].highestZ() == brick.lowestZ() - 1){
                        brick.supportedBy.add(highestSettled[pt.x][pt.y]);
                        highestSettled[pt.x][pt.y].supporting.add(brick);
                    }
                });
                if (!brick.supportedBy.isEmpty()) {
                    //unsettledBricks.remove(brick);
                    brick.getCrossSection().forEach(pt -> {
                        highestSettled[pt.x][pt.y] = brick;
                    });
                } else {
                    brick.descend();
                }
            }

            unsettledBricks = unsettledBricks.stream().filter(brick -> brick.supportedBy.isEmpty()).collect(Collectors.toList());
        }
    }


    public static class Brick {
        public List<Brick> supportedBy = new ArrayList<>();
        public List<Brick> supporting = new ArrayList<>();
        @NonNull Integer x1;
        @NonNull Integer y1;
        @NonNull Integer z1;
        @NonNull Integer x2;
        @NonNull Integer y2;
        @NonNull Integer z2;

        Brick(List<Integer> p1, List<Integer> p2) {
            x1 = p1.get(0);
            y1 = p1.get(1);
            z1 = p1.get(2);
            x2 = p2.get(0);
            y2 = p2.get(1);
            z2 = p2.get(2);
        }

        static Brick scanIn(String line) {
            String[] splitLine = line.split("~");
            String point1 = splitLine[0];
            List<Integer> p1 = Arrays.stream(point1.split(",")).map(Integer::parseInt).collect(Collectors.toList());
            List<Integer> p2 = Arrays.stream(splitLine[1].split(",")).map(Integer::parseInt).collect(Collectors.toList());
            return new Brick(p1, p2);
        }

        public int lowestZ() {
            return Math.min(z1, z2);
        }

        public Iterable<Point> getCrossSection() {
            Collection<Point> points = new ArrayList<>();
            for (int x=x1; x<=x2; x++){
                for (int y=y1; y<=y2; y++) {
                    points.add(new Point(x, y));
                }
            }
            return points;
        }

        public int highestZ() {
            return Math.max(z1, z2);
        }

        public void descend() {
            z1--;
            z2--;
        }

        public boolean isSafeToDisintegrate() {
            for (Brick brickThisIsSupporting : supporting) {
                if (brickThisIsSupporting.supportedBy.size() == 1) {
                    return false;
                }
            }
            return true;
        }
    }
}
