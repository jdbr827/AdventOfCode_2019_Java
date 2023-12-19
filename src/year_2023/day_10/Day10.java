package year_2023.day_10;

import utils.AOCScanner;
import viewModelUtil.CartesianPoint;
import viewModelUtil.CartesianTableModel;
import year_2019.day11.RotatingMovingRobot;
import year_2019.day15.model.CardinalDirection;

import java.util.List;
import java.util.Map;

public class Day10 {


    public static int furthestAwayPointInLoop(String inputFileName, Pipe startPipe) {
        AOCScanner day10Scanner = new AOCScanner(inputFileName);
        List<List<Character>> charArray = day10Scanner.scanAsCharMatrix();

        CartesianPoint startLocation = new CartesianPoint(0, 0);
        for(int i=0; i<charArray.size(); i++) {
            for (int j=0; j<charArray.get(0).size(); j++) {
                if (charArray.get(i).get(j) == 'S') {
                    startLocation = new CartesianPoint(i, j);
                }
            }
        }
        return new BugSimulator(startLocation, charArray, startPipe).findLoopLength() / 2;
    }
    



}

class BugSimulator {
    CartesianPoint currentLocation;
    CardinalDirection facing;
    List<List<Character>> pipeDiagram;
    CartesianPoint startLocation;
    Pipe startPipe;
    int stepsTaken = 0;

    public BugSimulator(CartesianPoint startLocation, List<List<Character>> charArray, Pipe startPipe) {
        this.startLocation = startLocation;
        this.pipeDiagram = charArray;
        this.startPipe = startPipe;
        facing = startPipe.direction1;
        this.currentLocation = startLocation;
    }


    Pipe getPipeAt(CartesianPoint location) {
        return Pipe.keyMap.getOrDefault(pipeDiagram.get(location.x).get(location.y), startPipe);
    }

    boolean simulateOneMovement() {
        currentLocation = new CartesianPoint(currentLocation.x-facing.velocity.y, currentLocation.y+facing.velocity.x);
        stepsTaken++;
        if (currentLocation.equals(startLocation)) {
            return true;
        }

        facing = getPipeAt(currentLocation).getDirectionThatIsNot(facing.opposite());
        return false;
    }


    public int findLoopLength() {
        while (!simulateOneMovement()) {}
        return stepsTaken;
    }
}


enum Pipe {
    NorthSouth(CardinalDirection.NORTH, CardinalDirection.SOUTH),
    EastWest(CardinalDirection.EAST, CardinalDirection.WEST),
    NorthEast(CardinalDirection.NORTH, CardinalDirection.EAST),
    NorthWest(CardinalDirection.NORTH, CardinalDirection.WEST),
    SouthWest(CardinalDirection.SOUTH, CardinalDirection.WEST),
    SouthEast(CardinalDirection.SOUTH, CardinalDirection.EAST),
    Ground(null, null);

    final static Map<Character, Pipe> keyMap = Map.of(
            '|', NorthSouth,
            '-', EastWest,
            'L', NorthEast,
            'J', NorthWest,
            '7', SouthWest,
            'F', SouthEast,
            '.', Ground
    );

    public static Pipe getPipeFromKey(Character key) {
        return keyMap.getOrDefault(key, Ground);
    }


    final CardinalDirection direction1;
    final CardinalDirection direction2;


    Pipe(CardinalDirection d1, CardinalDirection d2) {
        direction1 = d1;
        direction2 = d2;
    }
    public CardinalDirection getDirectionThatIsNot(CardinalDirection entryDirection) {
        if (direction1 == entryDirection) {
            return direction2;
        }
        return direction1;
    }
}