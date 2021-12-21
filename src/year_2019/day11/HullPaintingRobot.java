package year_2019.day11;

import year_2019.CartesianPoint;

import java.awt.*;

public class HullPaintingRobot{

    public enum Direction{
        UP(new Point(0, 1)),
        RIGHT(new Point(1, 0)),
        DOWN(new Point(0, -1)),
        LEFT(new Point(-1, 0));

        final Point velocity;

        Direction(Point velocity) {
            this.velocity = velocity;
        }
    }

    private CartesianPoint position = new CartesianPoint(0, 0);
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
        position.translate(getFacing().velocity.x, getFacing().velocity.y);
    }
}
