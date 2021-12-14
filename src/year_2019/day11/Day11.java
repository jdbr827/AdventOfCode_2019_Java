package year_2019.day11;

import year_2019.IntCodeComputer.IntCode;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;


public class Day11 {




    final static long[] DAY_10_PUZZLE_INPUT = new long[]{3, 8, 1005, 8, 320, 1106, 0, 11, 0, 0, 0, 104, 1, 104, 0, 3, 8, 102, -1, 8, 10, 101, 1, 10, 10, 4, 10, 1008, 8, 1, 10, 4, 10, 1001, 8, 0, 29, 2, 101, 10, 10, 3, 8, 102, -1, 8, 10, 1001, 10, 1, 10, 4, 10, 108, 1, 8, 10, 4, 10, 101, 0, 8, 54, 2, 3, 16, 10, 3, 8, 1002, 8, -1, 10, 101, 1, 10, 10, 4, 10, 1008, 8, 0, 10, 4, 10, 102, 1, 8, 81, 1006, 0, 75, 3, 8, 1002, 8, -1, 10, 1001, 10, 1, 10, 4, 10, 108, 0, 8, 10, 4, 10, 101, 0, 8, 105, 3, 8, 102, -1, 8, 10, 1001, 10, 1, 10, 4, 10, 1008, 8, 1, 10, 4, 10, 1001, 8, 0, 128, 3, 8, 1002, 8, -1, 10, 1001, 10, 1, 10, 4, 10, 108, 0, 8, 10, 4, 10, 102, 1, 8, 149, 1, 105, 5, 10, 1, 105, 20, 10, 3, 8, 102, -1, 8, 10, 101, 1, 10, 10, 4, 10, 108, 0, 8, 10, 4, 10, 1002, 8, 1, 179, 1, 101, 1, 10, 2, 109, 8, 10, 1006, 0, 74, 3, 8, 1002, 8, -1, 10, 101, 1, 10, 10, 4, 10, 1008, 8, 1, 10, 4, 10, 1001, 8, 0, 213, 1006, 0, 60, 2, 1105, 9, 10, 1, 1005, 11, 10, 3, 8, 1002, 8, -1, 10, 101, 1, 10, 10, 4, 10, 108, 1, 8, 10, 4, 10, 1002, 8, 1, 245, 1, 6, 20, 10, 1, 1103, 11, 10, 2, 6, 11, 10, 2, 1103, 0, 10, 3, 8, 1002, 8, -1, 10, 101, 1, 10, 10, 4, 10, 1008, 8, 0, 10, 4, 10, 1002, 8, 1, 284, 2, 1103, 12, 10, 2, 1104, 14, 10, 2, 1004, 12, 10, 2, 1009, 4, 10, 101, 1, 9, 9, 1007, 9, 968, 10, 1005, 10, 15, 99, 109, 642, 104, 0, 104, 1, 21102, 1, 48063419288L, 1, 21102, 1, 337, 0, 1105, 1, 441, 21101, 0, 846927340300L, 1, 21101, 0, 348, 0, 1105, 1, 441, 3, 10, 104, 0, 104, 1, 3, 10, 104, 0, 104, 0, 3, 10, 104, 0, 104, 1, 3, 10, 104, 0, 104, 1, 3, 10, 104, 0, 104, 0, 3, 10, 104, 0, 104, 1, 21102, 1, 235245104151L, 1, 21102, 395, 1, 0, 1105, 1, 441, 21102, 29032123584L, 1, 1, 21101, 0, 406, 0, 1105, 1, 441, 3, 10, 104, 0, 104, 0, 3, 10, 104, 0, 104, 0, 21101, 0, 709047878500L, 1, 21101, 429, 0, 0, 1106, 0, 441, 21101, 868402070284L, 0, 1, 21102, 1, 440, 0, 1105, 1, 441, 99, 109, 2, 22102, 1, -1, 1, 21101, 40, 0, 2, 21101, 0, 472, 3, 21102, 462, 1, 0, 1105, 1, 505, 109, -2, 2106, 0, 0, 0, 1, 0, 0, 1, 109, 2, 3, 10, 204, -1, 1001, 467, 468, 483, 4, 0, 1001, 467, 1, 467, 108, 4, 467, 10, 1006, 10, 499, 1102, 1, 0, 467, 109, -2, 2106, 0, 0, 0, 109, 4, 2101, 0, -1, 504, 1207, -3, 0, 10, 1006, 10, 522, 21101, 0, 0, -3, 22101, 0, -3, 1, 21202, -2, 1, 2, 21101, 1, 0, 3, 21102, 541, 1, 0, 1106, 0, 546, 109, -4, 2106, 0, 0, 109, 5, 1207, -3, 1, 10, 1006, 10, 569, 2207, -4, -2, 10, 1006, 10, 569, 21202, -4, 1, -4, 1105, 1, 637, 22102, 1, -4, 1, 21201, -3, -1, 2, 21202, -2, 2, 3, 21101, 588, 0, 0, 1105, 1, 546, 22102, 1, 1, -4, 21101, 0, 1, -1, 2207, -4, -2, 10, 1006, 10, 607, 21101, 0, 0, -1, 22202, -2, -1, -2, 2107, 0, -3, 10, 1006, 10, 629, 21201, -1, 0, 1, 21102, 629, 1, 0, 106, 0, 504, 21202, -2, -1, -2, 22201, -4, -2, -4, 109, -5, 2105, 1, 0};
    final static long WHITE = 1L;
    final static long BLACK = 0L;

    /**
     * Helper function to ensure no deadlock when brain halts
     * @param brain
     * @param outputs
     * @return
     * @throws InterruptedException
     */
    public static Optional<Long> takeOrConfirmDeath(IntCode brain, BlockingQueue<Long> outputs) throws InterruptedException {
        Optional<Long> paint = Optional.ofNullable(outputs.poll(2, TimeUnit.SECONDS));
        while(!paint.isPresent()) {
                if (!brain.isAlive()) {
                    System.out.println("LOCK!");
                    return Optional.empty();

                }
                paint = Optional.ofNullable(outputs.poll(2, TimeUnit.SECONDS));
            }
        return paint;
    }

    public Map<Point, Long> paintHull() throws InterruptedException {
        Map<Point, Long> hull = new HashMap<>();
        BlockingQueue<Long> statusAtPoint = new LinkedBlockingQueue<>();
        BlockingQueue<Long> outputs = new LinkedBlockingQueue<>();
        HullPaintingRobot robot = new HullPaintingRobot();

        IntCode brain = new IntCode(DAY_10_PUZZLE_INPUT, statusAtPoint, outputs);
        brain.start();

        statusAtPoint.add(hull.getOrDefault(robot.position, WHITE));

        Optional<Long> paint;
        while (!(paint = takeOrConfirmDeath(brain, outputs)).equals(Optional.empty())) {
            hull.put(robot.position, paint.get());

            long rotationDirection = outputs.take();
            if (rotationDirection == 1L) {robot.rotateClockwise();} else {robot.rotateCounterclockwise();}
            robot.moveForward();

            statusAtPoint.add(hull.getOrDefault(robot.position, BLACK));
        }
        System.out.println(hull);
        return hull;
        //return hull;
    }

    private static int updateCurrentDirectionIdx(int currentDirectionIdx, long direction) {
        currentDirectionIdx = (direction == 0)
                ? Math.floorMod(currentDirectionIdx - 1, 4)
                : Math.floorMod(currentDirectionIdx + 1, 4);
        return currentDirectionIdx;
    }

//    public static <T> Map<Point, T> convertCartesianToJavaCoordinates(Map<Point, T> cMap) {
//        int cXMax = cMap.keySet().stream().map((Point p) -> (int) p.x).max(Comparator.naturalOrder()).get();
//        int cXMin = cMap.keySet().stream().map((Point p) -> (int) p.x).min(Comparator.naturalOrder()).get();
//        int cYMax = cMap.keySet().stream().map((Point p) -> (int) p.y).max(Comparator.naturalOrder()).get();
//        int cYMin = cMap.keySet().stream().map((Point p) -> (int) p.y).min(Comparator.naturalOrder()).get();
//        int xdiff = cXMax-cXMin;
//        int ydiff = cYMax-cYMin;
//        Map<Point, T> jMap = new HashMap<>(Map.of());
//        for (Point p : cMap.keySet()) {
//            jMap.put(new Point(cYMax-p.y, p.x - cXMin), cMap.get(p));
//        }
//        return jMap;
//    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Day11Hull view = new Day11Hull();
    }
}
