package year_2019.day11;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static year_2019.day11.Day11.BLACK;

public class HullPainterModel {
    Map<Point, Long> hull = new HashMap<Point, Long>();
    HullPaintingRobot robot = new HullPaintingRobot();
    Map<Point, Boolean> painted = new HashMap<>();
    private int uniquePanelsPainted = 0;

    public HullPainterModel() {
    }

    public void paintPoint(Long paint) {
        if (!currentPointHasBeenPainted()) {uniquePanelsPainted++;}
        hull.put(robot.getPosition(), paint);
        painted.put(robot.getPosition(), true);
    }

    public Long getColorAtCurrentPoint() {
        return hull.getOrDefault(robot.getPosition(), BLACK);
    }


    private Boolean currentPointHasBeenPainted() {
        return painted.getOrDefault(robot.getPosition(), false);
    }

    public int getNumberOfUniquePanelsPainted() {
        return uniquePanelsPainted;
    }

    public void rotateRobot(long rotationInstruction) {
        robot.rotate(rotationInstruction);
    }

    public void moveRobotForward() {
        robot.moveForward();
    }

    public Point getCurrentRobotPosition() {
        return robot.getPosition();
    }

    public HullPaintingRobot.Direction getCurrentRobotFacing() {
        return robot.getFacing();
    }
}