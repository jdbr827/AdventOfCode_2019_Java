package year_2024.day_15;

import utils.AOCScanner;
import viewModelUtil.CartesianPoint;
import year_2019.day11.RotatingMovingRobot;
import year_2019.day15.model.CardinalDirection;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Warehouse {
    Character[][] grid;
    int Nrows;
    int Ncols;

    boolean isBlank(CartesianPoint pt) {
        return grid[pt.x][pt.y] == '.' || grid[pt.x][pt.y] == '@';
    }

    private boolean isWall(CartesianPoint pt) {
        return grid[pt.x][pt.y] == '#';
    }

    boolean isBox(CartesianPoint pt) {
        return grid[pt.x][pt.y] == 'O';
    }

    public void printGrid(CartesianPoint robotPosition) {
        grid[robotPosition.x][robotPosition.y] = '@';
        for (Character[] characters : grid) {
            System.out.println(Arrays.toString(characters));
        }
        System.out.println(" ");
        grid[robotPosition.x][robotPosition.y] = '.';
    }

    void scanInGrid(AOCScanner scanner) {
        List<Character[]> matrix = new java.util.ArrayList<>(List.of());
        String row;
        while (!(row = scanner.nextLine()).isEmpty()) {
            matrix.add(row.chars().mapToObj(c -> (char)c).toArray(Character[]::new));
        }
        Nrows = matrix.size();
        Ncols = matrix.get(0).length;

        Character[][] arr = new Character[Nrows][Ncols];
        for (int i=0; i<Nrows; i++) {
            arr[i] = matrix.get(i);
        }
        grid = arr;
    }

    CartesianPoint findRobotStartPosition() {
        for (int i = 0; i < Nrows; i++) {
            for (int j = 0; j < Ncols; j++) {
                if (grid[i][j] == '@') {
                    return new CartesianPoint(i, j);
                }
            }
        }
        return null;
    }

    int getGPSSum() {
        int tot = 0;
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                if (isBox(new CartesianPoint(i, j))) {
                    tot += 100*i + j;
                }
            }
        }
        return tot;
    }

}

public class Day15 {
    Warehouse warehouse = new Warehouse();
    RotatingMovingRobot robot;
    List<CardinalDirection> instructions;

    public Day15(String inputFilename) {
        AOCScanner scanner = new AOCScanner(inputFilename);
        warehouse.scanInGrid(scanner);
        scanInInstructions(scanner);
        robot = new RotatingMovingRobot(warehouse.findRobotStartPosition(), CardinalDirection.NORTH);
        warehouse.grid[robot.getPosition().x][robot.getPosition().y] = '.';
        //System.out.println(instructions);
    }

    private void scanInInstructions(AOCScanner scanner) {
        instructions = new LinkedList<>();
        while (scanner.hasNextLine()) {
            for (char c : scanner.nextLine().toCharArray()) {
                switch (c) {
                    case '^':
                        instructions.add(CardinalDirection.WEST);
                        break;
                    case 'v':
                        instructions.add(CardinalDirection.EAST);
                        break;
                    case '>':
                        instructions.add(CardinalDirection.NORTH);
                        break;
                    case '<':
                        instructions.add(CardinalDirection.SOUTH);
                        break;
                    default:
                        throw new Error("Cannot recognize instruction "+ c);
                }
            }
        }
    }






    public int sumOfGPSAfterMoving() {
        for (CardinalDirection instruction : instructions) {
            attemptMove(instruction);
            //printGrid();
        }
        return warehouse.getGPSSum();
    }



    private void attemptMove(CardinalDirection instruction) {
        robot.setFacing(instruction);
        CartesianPoint pt = robot.getPosition().add(instruction.velocity);
        if (warehouse.isBlank(pt)) {
            robot.moveForward();
            return;
        }
        while (warehouse.isBox(pt)) {
            pt = pt.add(instruction.velocity);
        };
        if (warehouse.isBlank(pt)) {
            CartesianPoint initialPt = robot.getPosition().add(instruction.velocity);
            warehouse.grid[initialPt.x][initialPt.y] = '.';
            warehouse.grid[pt.x][pt.y] = 'O';
            robot.moveForward();
        }
        // otherwise we have hit a wall and the move does not happen;

    }



    public int sumOfGPSAfterMovingScaledUp() {
        return 0;
    }
}
