package year_2019.day11;

import lombok.Getter;
import lombok.NoArgsConstructor;
import viewModelUtil.CartesianPoint;
import year_2019.day15.model.CardinalDirection;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static year_2019.day11.Day11.BLACK;

@NoArgsConstructor
public class HullPainterModel {
    Map<Point, Long> hull = new HashMap<>();
    HullPaintingRobot robot = new HullPaintingRobot();
    Map<Point, Boolean> isPainted = new HashMap<>();
    @Getter private int uniquePanelsPainted = 0;

    public void paintCurrentPoint(Long paint) {
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

    public CartesianPoint getCurrentRobotPosition() {
        return robot.getPosition();
    }

    public CardinalDirection getCurrentRobotFacing() {
        return robot.getFacing();
    }

    private Boolean currentPointHasBeenPainted() {
        return isPainted.getOrDefault(robot.getPosition(), false);
    }


}