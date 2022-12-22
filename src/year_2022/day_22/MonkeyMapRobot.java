package year_2022.day_22;

import year_2019.day11.RotatingMovingRobot;
import year_2019.day15.model.CardinalDirection;

public class MonkeyMapRobot extends RotatingMovingRobot {

    public MonkeyMapRobot() {
        super(CardinalDirection.EAST);
    }

    @Override
    public void rotateClockwise() {
        super.rotateClockwise();
    }

    @Override
    public void rotateCounterclockwise() {
        super.rotateCounterclockwise();
    }

    public void turnAround() {
        super.rotateClockwise();
        super.rotateClockwise();
    }
}
