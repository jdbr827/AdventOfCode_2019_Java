package year_2023.day_16;

import viewModelUtil.CartesianPoint;
import year_2019.day11.RotatingMovingRobot;
import year_2019.day15.model.CardinalDirection;

public class JavaRotatingMovingRobot extends RotatingMovingRobot {

    protected JavaRotatingMovingRobot(CardinalDirection initiallyFacing) {
        super(initiallyFacing);
    }

    public JavaRotatingMovingRobot(CartesianPoint initialPosition, CardinalDirection initiallyFacing) {
        super(initialPosition, initiallyFacing);
    }

    public void moveForward() {
        position = new CartesianPoint(getPosition().x - facing.velocity.y, getPosition().y + facing.velocity.x);
    }

    public void moveBackward() {
        position = new CartesianPoint(getPosition().x + facing.velocity.y, getPosition().y - facing.velocity.x);
    }


}
