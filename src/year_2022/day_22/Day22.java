package year_2022.day_22;

import viewModelUtil.CartesianPoint;
import year_2019.day15.model.CardinalDirection;

import java.io.FileNotFoundException;
import java.util.List;

public class Day22 {
    IMonkeyMapDiagram diagram;
    MonkeyMapRobot robot;
    List<MonkeyInstruction> instructions;

    public static int part1(String fileName) throws FileNotFoundException {
        Day22 solver = new Day22(fileName);
        return solver.findPassword();
    }

    Day22(String fileName) throws FileNotFoundException {
        Day22Scanner scanner = new Day22Scanner(fileName);
        diagram = new MonkeyMapDiagram(scanner.readInDiagram());
        robot = new MonkeyMapRobot();
        instructions = scanner.scanInstructions();

    }

    public int findPassword() {
        // start at left-most open space
        while (diagram.readAtCartesianPoint(robot.getPosition()) != MonkeyMapEnum.OPEN_SPACE) {
            robot.moveForward();
        }

        processInstructions();

        return calculatePassword();
    }

    private int calculatePassword() {
        CartesianPoint position = robot.getPosition();
        int y = (int) position.getX() + 1;
        int x = (int) -position.getY() + 1;
        int f = -1;

        switch (robot.getFacing()) {
            case EAST:
                f = 0;
                break;
            case SOUTH:
                f= 1;
                break;
            case WEST:
                f = 2;
                break;
            case NORTH:
                f = 3;
                break;
        }

        return 1000 * x + 4 * y + f;

    }

    private void processInstructions() {
        for (MonkeyInstruction instruction : instructions) {
            for (int i=0; i<instruction.numForward; i++) {
                attemptMoveForward();
            }
            if (instruction.rotationDirection != null) {
                switch (instruction.rotationDirection) {
                    case 'R':
                        robot.rotateClockwise();
                        break;
                    case 'L':
                        robot.rotateCounterclockwise();
                        break;
                    default: // null cause of bad lengths
                        break;
                }
            }
        }
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
        //System.out.println(robot.getPosition());
    }
}
