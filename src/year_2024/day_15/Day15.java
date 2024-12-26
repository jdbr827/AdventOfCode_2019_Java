package year_2024.day_15;

import utils.AOCScanner;
import viewModelUtil.CartesianPoint;
import year_2019.day11.RotatingMovingRobot;
import year_2019.day15.model.CardinalDirection;

import javax.smartcardio.Card;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Day15 {
    Character[][] grid;
    RotatingMovingRobot robot;
    List<CardinalDirection> instructions;

    public Day15(String inputFilename) {
        AOCScanner scanner = new AOCScanner(inputFilename);
        List<Character[]> matrix = new java.util.ArrayList<>(List.of());
        String row;
        while (!(row = scanner.nextLine()).isEmpty()) {
            matrix.add(row.chars().mapToObj(c -> (char)c).toArray(Character[]::new));
        }
        int Nrows = matrix.size();
        int Ncols = matrix.get(0).length;

        Character[][] arr = new Character[Nrows][Ncols];
        for (int i=0; i<Nrows; i++) {
            arr[i] = matrix.get(i);
        }
        grid = arr;

        for (int i=0; i<Nrows; i++) {
            for (int j=0; j<Ncols; j++) {
                if (grid[i][j] == '@') {
                    robot = new RotatingMovingRobot(new CartesianPoint(i, j), CardinalDirection.NORTH);
                }
            }
        }

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
        grid[robot.getPosition().x][robot.getPosition().y] = '.';
        //System.out.println(instructions);
    }

    public void printGrid() {
        grid[robot.getPosition().x][robot.getPosition().y] = '@';
        for (Character[] characters : grid) {
            System.out.println(Arrays.toString(characters));
        }
        System.out.println(" ");
        grid[robot.getPosition().x][robot.getPosition().y] = '.';
    }




    public int sumOfGPSAfterMoving() {
        for (CardinalDirection instruction : instructions) {
            attemptMove(instruction);
            //printGrid();
        }
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

    private void attemptMove(CardinalDirection instruction) {
        robot.setFacing(instruction);
        CartesianPoint pt = robot.getPosition().add(instruction.velocity);
        if (isBlank(pt)) {
            robot.moveForward();
            return;
        }
        while (isBox(pt)) {
            pt = pt.add(instruction.velocity);
        };
        if (isBlank(pt)) {
            CartesianPoint initialPt = robot.getPosition().add(instruction.velocity);
            grid[initialPt.x][initialPt.y] = '.';
            grid[pt.x][pt.y] = 'O';
            robot.moveForward();
        }
        // otherwise we have hit a wall and the move does not happen;

    }

    private boolean isBlank(CartesianPoint pt) {
        return grid[pt.x][pt.y] == '.' || grid[pt.x][pt.y] == '@';
    }

    private boolean isWall(CartesianPoint pt) {
        return grid[pt.x][pt.y] == '#';
    }

    private boolean isBox(CartesianPoint pt) {
        return grid[pt.x][pt.y] == 'O';
    }
}
