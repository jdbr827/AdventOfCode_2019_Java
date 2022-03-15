package year_2019.day11;

import static year_2019.day15.model.CardinalDirection.*;

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
