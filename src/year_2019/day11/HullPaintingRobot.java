package year_2019.day11;

import year_2019.CartesianPoint;
import year_2019.day15.CardinalDirection;

import java.awt.*;

import static year_2019.day15.CardinalDirection.*;

/**
 * Data Structure for the position and direction of the robot
 */
public class HullPaintingRobot {

    public enum Direction{
        UP(CardinalDirection.NORTH),
        RIGHT(CardinalDirection.EAST),
        DOWN(CardinalDirection.SOUTH),
        LEFT(CardinalDirection.WEST);

        CardinalDirection cardinalDirection;

        Direction(CardinalDirection cardinalDirection) {
            this.cardinalDirection = cardinalDirection;
        }
    }

    private final CartesianPoint position = new CartesianPoint(0, 0);
    private Direction facing = Direction.UP;

    public CartesianPoint getPosition() {
        return (CartesianPoint) position.clone();
    }

    public Direction getFacing() {
        return facing;
    }

    public void rotate(long rotationInstruction) {
        if (rotationInstruction == 1L) {rotateClockwise();} else {rotateCounterclockwise();}
    }

    private void rotateClockwise() {
        setFacing(Direction.values()[Math.floorMod(getFacing().ordinal() + 1, 4)]);
    }

    private void rotateCounterclockwise() {
        setFacing(Direction.values()[Math.floorMod(getFacing().ordinal() - 1, 4)]);
    }

    private void setFacing(Direction value) {
        facing = value;
    }

    public void moveForward() {
        position.translate(getFacing().cardinalDirection.velocity.x, getFacing().cardinalDirection.velocity.y);
    }
}
