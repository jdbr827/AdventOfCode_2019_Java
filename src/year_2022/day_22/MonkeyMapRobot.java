package year_2022.day_22;

import viewModelUtil.CartesianPoint;
import year_2019.day11.RotatingMovingRobot;
import year_2019.day15.model.CardinalDirection;

public class MonkeyMapRobot extends RotatingMovingRobot implements IMonkeyMapRobot {
    protected IMonkeyMapDiagram diagram;

    public MonkeyMapRobot(IMonkeyMapDiagram diagram) {
        super(CardinalDirection.EAST);
        this.diagram = diagram;
    }

    @Override
    public void rotateClockwise() {
        super.rotateClockwise();
    }

    @Override
    public void rotateCounterclockwise() {
        super.rotateCounterclockwise();
    }

    @Override
    public void moveForward() {
        this.position = previewMoveForward();
    }

    public void moveTo(CartesianPoint p) {
        this.position = p;
    }

    public void moveTo(int x, int y) {
        moveTo(new CartesianPoint(x, y));
    }


    public CartesianPoint previewMoveForward() {
        return diagram.getNextPointInDirection(getPosition(), getFacing());
    }

    public MonkeyMapEnum readCurrentPosition() {
        return diagram.readAtCartesianPoint(getPosition());
    }

    public MonkeyMapEnum readDesiredPosition() {
        return diagram.readAtCartesianPoint(previewMoveForward());
    }


    public void turnAround() {
        super.rotateClockwise();
        super.rotateClockwise();
    }
}
