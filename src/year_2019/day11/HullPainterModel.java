package year_2019.day11;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static year_2019.day11.Day11.BLACK;

public class HullPainterModel {
    Map<Point, Long> hull = new HashMap<Point, Long>();
    HullPaintingRobot robot = new HullPaintingRobot();
    Map<Point, Boolean> painted = new HashMap<>();

    public HullPainterModel() {
    }

    private void rotateRobotClockwise() {
        robot.rotateClockwise();
    }

    private void rotateRobotCounterclockwise() {
        robot.rotateCounterclockwise();
    }

    public void rotateRobot(long rotationInstruction) {
        if (rotationInstruction == 1L) {rotateRobotClockwise();} else {rotateRobotCounterclockwise();}
    }

    public Long getColorAtCurrentPoint() {
        return hull.getOrDefault(robot.position, BLACK);
    }

    public void paintPoint(Long paint) {
        hull.put(robot.position, paint);
        painted.put(robot.position, true);

    }
}