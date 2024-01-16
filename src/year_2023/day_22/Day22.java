package year_2023.day_22;

import lombok.NonNull;
import utils.AOCScanner;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Day22 {
    private static final Brick GROUND = new Brick("0,0,0~1000,1000,0");
    Collection<Brick> bricks = new ArrayList<>();
    Brick[][] highestSettled;
     int xMax;
     int yMax;

    public Day22(String fileName) {
        AOCScanner scanner = new AOCScanner(fileName);
        scanner.forEachLine(line -> {
            bricks.add(new Brick(line));
        });
        xMax = bricks.stream().map(brick -> brick.x2).max(Comparator.naturalOrder()).get();
        yMax = bricks.stream().map(brick -> brick.y2).max(Comparator.naturalOrder()).get();
        highestSettled = new Brick[xMax+1][yMax+1];
          for (int x=0; x<xMax+1; x++) {
            for (int y=0; y<yMax+1; y++) {
                highestSettled[x][y] = GROUND;
            }
        }

    }

    public int getNumToSafelyDisintegrate() {
        simulateBrickFalling();
        return countNumToSafelyDisintegrate();
    }

    private int countNumToSafelyDisintegrate() {
        bricks.stream().filter(Brick::isSafeToDisintegrate).forEach(brick -> System.out.println(brick.line));
        return (int) bricks.stream().filter(Brick::isSafeToDisintegrate).count();
    }

    private void simulateBrickFalling() {
        List<Brick> unsettledBricks = bricks.stream().sorted(Comparator.comparing(Brick::lowestZ)).collect(Collectors.toList());
        while (!unsettledBricks.isEmpty()) {
            for (Brick brick : unsettledBricks) {
                brick.checkIfSupported(this);
                if (brick.isSupported()) {
                    brick.markAsSettled(this);
                } else {
                    brick.descend();
                }
            }

            unsettledBricks = unsettledBricks.stream().filter(brick -> !brick.isSupported()).collect(Collectors.toList());
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
        String line;


        Brick(String line) {
            this.line = line;
            String[] splitLine = line.split("~");
            String point1 = splitLine[0];
            List<Integer> p1 = Arrays.stream(point1.split(",")).map(Integer::parseInt).collect(Collectors.toList());
            List<Integer> p2 = Arrays.stream(splitLine[1].split(",")).map(Integer::parseInt).collect(Collectors.toList());
            x1 = p1.get(0);
            y1 = p1.get(1);
            z1 = p1.get(2);
            x2 = p2.get(0);
            y2 = p2.get(1);
            z2 = p2.get(2);
        }

        public int lowestZ() {
            return Math.min(z1, z2);
        }

        public Iterable<Point> getCrossSection() {
            Collection<Point> points = new ArrayList<>();
            for (int x=Math.min(x1, x2); x<=Math.max(x1, x2); x++){
                for (int y=Math.min(y1, y2); y<=Math.max(y1, y2); y++) {
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

        private void markBrickIsSupportedBy(Brick highestSettledAtThisPoint) {
            supportedBy.add(highestSettledAtThisPoint);
            highestSettledAtThisPoint.supporting.add(this);
        }

        private boolean isSupported() {
            return !supportedBy.isEmpty();
        }

        private void markAsSettled(Day22 day22) {
            //unsettledBricks.remove(brick);
            getCrossSection().forEach(pt -> {
                day22.highestSettled[pt.x][pt.y] = this;
            });
        }

        private void checkIfSupported(Day22 day22) {
            getCrossSection().forEach(pt -> {
                Brick highestSettledAtThisPoint = day22.highestSettled[pt.x][pt.y];
                if (highestSettledAtThisPoint.highestZ() == lowestZ() - 1){
                    markBrickIsSupportedBy(highestSettledAtThisPoint);
                }
            });
        }
    }
}
