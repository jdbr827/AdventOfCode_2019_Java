package year_2019.day15;

import java.awt.*;

public enum CardinalDirection {
    NORTH(1L, new Point(0, 1)),
    EAST(3L, new Point(1, 0)),
    SOUTH(2L, new Point(0, -1)),
    WEST(4L, new Point(-1, 0));

    final long inputInstruction;
    final Point velocity;


    CardinalDirection(long inputInstruction, Point velocity) {
        this.inputInstruction = inputInstruction;
        this.velocity = velocity;
    }

    CardinalDirection opposite() {
        return CardinalDirection.values()[Math.floorMod(this.ordinal() + 2, 4)];
    }

    CardinalDirection clockwise() {
        return CardinalDirection.values()[Math.floorMod(this.ordinal() + 1, 4)];
    }

    CardinalDirection counterclockwise() {
        return CardinalDirection.values()[Math.floorMod(this.ordinal() + 3, 4)];
    }
}
