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

    Point position = new Point(0, 0);
    Direction facing = Direction.UP;

    public void rotateClockwise() {
        facing = Direction.values()[Math.floorMod(facing.ordinal() + 1, 4)];
    }

    public void rotateCounterclockwise() {
        facing = Direction.values()[Math.floorMod(facing.ordinal() - 1, 4)];
    }

    public void moveForward() {
        position = new Point(
                position.x + facing.velocity.x,
                position.y + facing.velocity.y
        );
    }
}
