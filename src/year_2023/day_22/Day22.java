package year_2023.day_22;

import lombok.Getter;
import lombok.NonNull;
import utils.AOCScanner;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Day22 {
    private static final Brick GROUND = new Brick("0,0,0~1000,1000,0", 0);
    Collection<Brick> bricks = new ArrayList<>();
    Brick[][] highestSettled;
    int xMax;
    int yMax;
    static Set<Brick> falling = new HashSet<>();

    public Day22(String fileName) {
        AOCScanner scanner = new AOCScanner(fileName);
        AtomicInteger id= new AtomicInteger(1);
        scanner.forEachLine(line -> {
            bricks.add(new Brick(line, id.get()));
            id.getAndIncrement();
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
        bricks.stream()
                .sorted(Comparator.comparing(Brick::lowestZ))
                //.filter(Brick::isSafeToDisintegrate)
                .forEach(brick -> {
                    StringBuilder sb = new StringBuilder();
                    sb.append(brick.brickId).append(" | ").append(brick.line).append(" --> ").append(brick.getCurrentLocation()).append(" | ");
                    for (Brick supBy : brick.supportedBy) {
                        sb.append(supBy.brickId);
                        sb.append(",");
                    }
                    System.out.println(sb);
                });
        return (int) bricks.stream().filter(Brick::isSafeToDisintegrate).count();
    }

    private void simulateBrickFalling() {
        List<Brick> bricksOrderedByLowestZ = bricks.stream().sorted(Comparator.comparing(Brick::lowestZ)).collect(Collectors.toList());
        for (Brick brick : bricksOrderedByLowestZ) {
            while (!brick.isSupported()) {
                checkIfSupported(brick);
                if (brick.isSupported()) {
                    markAsSettled(brick);
                } else {
                    brick.descend();
                }
            }
        }
    }

    private void markAsSettled(Brick brick) {
        brick.getXyCrossSection().forEach(pt -> highestSettled[pt.x][pt.y] = brick);
    }

    private void checkIfSupported(Brick brick) {
        brick.getXyCrossSection().forEach(pt -> {
            Brick highestSettledAtThisPoint = this.highestSettled[pt.x][pt.y];
            if (highestSettledAtThisPoint.highestZ() == brick.lowestZ() - 1){
                brick.markBrickIsSupportedBy(highestSettledAtThisPoint);
            }
        });
    }

    public int getBiggestChainReactionNum() {
        int total = 0;
        simulateBrickFalling();
        for (Brick toDisintegrate : bricks) {
            falling = new HashSet<>();
            total += toDisintegrate.numOtherBricksThatFallIfDisintegrated();
        }
        return total;
    }


    public static class Brick {
        int brickId;
        public Set<Brick> supportedBy = new HashSet<>();
        public Set<Brick> supporting = new HashSet<>();
        @NonNull final Integer x1;
        @NonNull final Integer y1;
        @NonNull Integer z1;
        @NonNull final Integer x2;
        @NonNull final Integer y2;
        @NonNull Integer z2;
        String line;

        @Getter
        Iterable<Point> xyCrossSection;


        Brick(String line, int brickId) {
            this.line = line;
            this.brickId = brickId;
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

            Collection<Point> points = new ArrayList<>();
            for (int x=Math.min(x1, x2); x<=Math.max(x1, x2); x++){
                for (int y=Math.min(y1, y2); y<=Math.max(y1, y2); y++) {
                    points.add(new Point(x, y));
                }
            }
            xyCrossSection = points;

        }

        String getCurrentLocation() {
            return x1 + "," + y1 + "," + z1 + "~" + x2 + "," + y2 + "," + z2;
        }

        public int lowestZ() {
            return Math.min(z1, z2);
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
                if (brickThisIsSupporting.supportedBy.equals(Set.of(this))) {
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

        public int numOtherBricksThatFallIfDisintegrated() {
            int total = 0;
            falling.add(this);
            for (Brick supported : this.supporting) {
                if (falling.containsAll(supported.supportedBy))
                    total += 1 + supported.numOtherBricksThatFallIfDisintegrated();
            }
            return total;
        }
    }
}
