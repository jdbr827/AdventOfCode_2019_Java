package year_2019.day11;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static year_2019.day11.Day11.BLACK;

public class HullPainterModel {
    Map<Point, Long> hull = new HashMap<Point, Long>();
    HullPaintingRobot robot = new HullPaintingRobot();
    Map<Point, Boolean> isPainted = new HashMap<>();
    private int uniquePanelsPainted = 0;

    public HullPainterModel() {
    }

    public void paintPoint(Long paint) {
        if (!currentPointHasBeenPainted()) {uniquePanelsPainted++;}
        hull.put(robot.getPosition(), paint);
        isPainted.put(robot.getPosition(), true);
    }

    public Long getColorAtCurrentPoint() {
        return hull.getOrDefault(robot.getPosition(), BLACK);
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

    private Boolean currentPointHasBeenPainted() {
        return isPainted.getOrDefault(robot.getPosition(), false);
    }

    public int getNumberOfUniquePanelsPainted() {
        return uniquePanelsPainted;
    }

}