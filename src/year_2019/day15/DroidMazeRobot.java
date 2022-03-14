package year_2019.day15;

import year_2019.CartesianPoint;

import java.util.Stack;

public class DroidMazeRobot {
    private CartesianPoint droidLocation = new CartesianPoint(0, 0);
    Stack<CardinalDirection> directionStack;
    CardinalDirection attemptDirection;
    final CardinalDirection startDirection;

    public DroidMazeRobot() {
        this.startDirection = CardinalDirection.NORTH;
    }

    void moveDroid(CardinalDirection direction) {
        getDroidLocation().translate(direction.velocity.x, direction.velocity.y);
    }

    public CartesianPoint getDroidLocation() {
        return (CartesianPoint) droidLocation.clone();
    }
}