package year_2022.day_22;

import viewModelUtil.CartesianPoint;
import year_2019.day15.model.CardinalDirection;

import java.io.FileNotFoundException;
import java.util.List;

public class Day22 {
    IMonkeyMapDiagram diagram;
    MonkeyMapRobot robot;

    public static int part1(String fileName) throws FileNotFoundException {
        Day22 solver = new Day22(fileName);
        return 0;
    }

    Day22(String fileName) throws FileNotFoundException {
        Day22Scanner scanner = new Day22Scanner(fileName);
        diagram = new MonkeyMapDiagram(scanner.readInDiagram());
        robot = new MonkeyMapRobot();

        // start at left-most open space
        while (diagram.readAtCartesianPoint(robot.getPosition()) != MonkeyMapEnum.OPEN_SPACE) {
            robot.moveForward();
        }

        attemptMoveForward();
        attemptMoveForward();
        attemptMoveForward();
        robot.rotateCounterclockwise();
        attemptMoveForward();
    }

    void attemptMoveForward() {
        CartesianPoint desiredNewPoint = robot.previewMoveForward();
        switch (diagram.readAtCartesianPoint(desiredNewPoint)) {
            case WALL:
                break; // you cant move forward;
            case OPEN_SPACE:
                robot.moveForward();
                break;
            case WARP_ZONE:
                robot.turnAround();
                while (diagram.readAtCartesianPoint(robot.previewMoveForward()) != MonkeyMapEnum.WARP_ZONE) {
                    robot.moveForward();
                }
                robot.turnAround();
                if (diagram.readAtCartesianPoint(robot.getPosition()) == MonkeyMapEnum.WALL) {
                    while (diagram.readAtCartesianPoint(robot.previewMoveForward()) != MonkeyMapEnum.WARP_ZONE) {
                        robot.moveForward();
                    }
                }
                break;
        }
        System.out.println(robot.getPosition());
    }
}
