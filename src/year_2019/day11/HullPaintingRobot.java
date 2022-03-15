package year_2019.day11;

import year_2019.CartesianPoint;
import year_2019.day15.CardinalDirection;

import java.awt.*;

import static year_2019.day15.CardinalDirection.*;

/**
 * Data Structure for the position and direction of the robot
 */
public class HullPaintingRobot extends RotatingMovingRobot {

    HullPaintingRobot() {
        super(NORTH);
    }

    public void rotate(long rotationInstruction) {
        if (rotationInstruction == 1L) {rotateClockwise();} else {rotateCounterclockwise();}
    }
}
