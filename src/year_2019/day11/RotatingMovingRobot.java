package year_2019.day11;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.units.qual.C;
import viewModelUtil.CartesianPoint;
import year_2019.day15.model.CardinalDirection;

/**
 * Utility class for a robot on a cartesian plane that can
 * - Rotate Clockwise or Counterclockwise 90 degrees at a time
 * - Move Forward in whatever direction they are facing.
 */
@AllArgsConstructor
public class RotatingMovingRobot {

    protected CartesianPoint position;
    @Getter @Setter protected CardinalDirection facing;


    protected RotatingMovingRobot(CardinalDirection initiallyFacing) {
        this(new CartesianPoint(0, 0), initiallyFacing);
    }

    public CartesianPoint getPosition() {
        return (CartesianPoint) position.clone();
    }

    public void moveForward() {
        position.translate(getFacing().velocity.x, getFacing().velocity.y);
    }

    public void rotateClockwise() {
        setFacing(CardinalDirection.values()[Math.floorMod(getFacing().ordinal() + 1, 4)]);
    }

    public void rotateCounterclockwise() {
        setFacing(CardinalDirection.values()[Math.floorMod(getFacing().ordinal() - 1, 4)]);
    }


}
