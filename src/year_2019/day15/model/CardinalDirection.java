package year_2019.day15.model;

import java.awt.*;

public enum CardinalDirection {
    NORTH(1L, new Point(0, 1), 'N'),
    EAST(3L, new Point(1, 0), 'E'),
    SOUTH(2L, new Point(0, -1), 'S'),
    WEST(4L, new Point(-1, 0), 'W');

    final long inputInstruction;
    public final Point velocity;
    final char shortName;


    CardinalDirection(long inputInstruction, Point velocity, char shortName) {
        this.inputInstruction = inputInstruction;
        this.velocity = velocity;
        this.shortName = shortName;
    }

    public CardinalDirection opposite() {
        return CardinalDirection.values()[Math.floorMod(this.ordinal() + 2, 4)];
    }

    public CardinalDirection clockwise() {
        return CardinalDirection.values()[Math.floorMod(this.ordinal() + 1, 4)];
    }

    public CardinalDirection counterclockwise() {
        return CardinalDirection.values()[Math.floorMod(this.ordinal() + 3, 4)];
    }

    public char getShortName() {
        return shortName;
    }
}
