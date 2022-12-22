package year_2022.day_22;

import viewModelUtil.CartesianPoint;
import year_2019.day11.RotatingMovingRobot;
import year_2019.day15.model.CardinalDirection;

public class MonkeyMapRobot extends RotatingMovingRobot {
    IMonkeyMapDiagram diagram;

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


    public CartesianPoint previewMoveForward() {
        return new CartesianPoint(position.x + getFacing().velocity.x, position.y + getFacing().velocity.y);
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
