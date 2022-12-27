package year_2019.day11;

import lombok.NoArgsConstructor;
import viewModelUtil.CartesianPoint;
import year_2019.day15.model.CardinalDirection;

import static year_2019.day15.model.CardinalDirection.*;

/**
 * Data Structure for the position and direction of the robot
 */
@NoArgsConstructor
public class HullPaintingRobot {
    private final RotatingMovingRobot underlyingRobot = new RotatingMovingRobot(NORTH);

    public void rotate(long rotationInstruction) {
        if (rotationInstruction == 1L) {underlyingRobot.rotateClockwise();} else {underlyingRobot.rotateCounterclockwise();}
    }

    public CartesianPoint getPosition() {
        return underlyingRobot.getPosition();
    }

    public void moveForward() {
        underlyingRobot.moveForward();
    }

    public CardinalDirection getFacing() {
        return underlyingRobot.getFacing();
    }
}
