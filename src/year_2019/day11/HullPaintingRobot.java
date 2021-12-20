package year_2019.day11;

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

    private Point position = new Point(0, 0);
    Direction facing = Direction.UP;

    public Point getPosition() {
        return position;
    }

    public void rotate(long rotationInstruction) {
        if (rotationInstruction == 1L) {rotateClockwise();} else {rotateCounterclockwise();}
    }

    private void rotateClockwise() {
        facing = Direction.values()[Math.floorMod(facing.ordinal() + 1, 4)];
    }

    private void rotateCounterclockwise() {
        facing = Direction.values()[Math.floorMod(facing.ordinal() - 1, 4)];
    }

    public void moveForward() {
        position = new Point(
                getPosition().x + facing.velocity.x,
                getPosition().y + facing.velocity.y
        );
    }
}
