package year_2022.day_22;

import viewModelUtil.CartesianPoint;
import year_2022.day_22.IMonkeyMapDiagram;
import year_2022.day_22.MonkeyMapRobot;

public class CubeMonkeyMapRobot implements IMonkeyMapRobot {
    protected IMonkeyMapDiagram diagram;

    public CubeMonkeyMapRobot(IMonkeyMapDiagram diagram) {
        this.diagram = diagram;
    }


//    public CartesianPoint previewMoveForward() {
//        return diagram.getNextPointInDirection(getPosition(), getFacing());
//    }

    @Override
    public void moveForward() {

    }

    @Override
    public void rotateClockwise() {

    }

    @Override
    public void rotateCounterclockwise() {

    }
}
