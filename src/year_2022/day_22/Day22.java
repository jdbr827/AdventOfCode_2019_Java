package year_2022.day_22;

import viewModelUtil.CartesianPoint;

import java.io.FileNotFoundException;
import java.util.List;

public class Day22 {
    MonkeyMapRobot robot;
    List<MonkeyInstruction> instructions;

    public static int part1(String fileName) throws FileNotFoundException {
        Day22 solver = new Day22(fileName, 0);
        return solver.findPassword();
    }

    public static int part2(String fileName, int n) throws FileNotFoundException {
        Day22 solver = new Day22(fileName, n);
        return solver.findPasswordCube();
    }

    Day22(String fileName, int n) throws FileNotFoundException {
        Day22Scanner scanner = new Day22Scanner(fileName);
        IMonkeyMapDiagram diagram = n == 0 ? new MonkeyMapDiagram(scanner.readInDiagram()) : new MonkeyMapCubeDiagram(n, scanner.readInDiagram());
        robot = new MonkeyMapRobot(diagram);
        instructions = scanner.scanInstructions();
        //System.out.println(diagram.readAtCartesianPoint(2, -6));

    }



    public int findPasswordCube() {
        return 0;
    }

    public int findPassword() {
        moveRobotToStartSpace();
        processInstructions();
        return calculatePassword();
    }

    private void moveRobotToStartSpace() {
        // start at left-most open space
        int y=0;
        while (robot.readCurrentPosition() != MonkeyMapEnum.OPEN_SPACE) {
            robot.moveTo(0, y);
            y--;
        }
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
        switch (robot.readDesiredPosition()) {
            case WALL:
                break; // you cant move forward;
            case OPEN_SPACE:
                robot.moveForward();
                break;
            // should only happen in part 1?
            case WARP_ZONE:
                robot.turnAround();
                while (robot.readDesiredPosition() != MonkeyMapEnum.WARP_ZONE) {
                    robot.moveForward();
                }
                robot.turnAround();
                if (robot.readCurrentPosition() == MonkeyMapEnum.WALL) {
                    while (robot.readDesiredPosition() != MonkeyMapEnum.WARP_ZONE) {
                        robot.moveForward();
                    }
                }
                break;
        }
        //System.out.println(robot.getPosition());
    }
}
