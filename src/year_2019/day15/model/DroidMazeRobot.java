package year_2019.day15.model;

import year_2019.CartesianPoint;
import year_2019.day11.RotatingMovingRobot;

public class DroidMazeRobot extends RotatingMovingRobot {

    CardinalDirection attemptDirection;
    final CardinalDirection startDirection;

    public DroidMazeRobot() {
        super(CardinalDirection.NORTH);
        this.startDirection = CardinalDirection.NORTH;
    }

    void moveDroid(CardinalDirection direction) {
        position.translate(direction.velocity.x, direction.velocity.y);
    }

    public CartesianPoint getDroidLocation() {
        return (CartesianPoint) position.clone();
    }
}