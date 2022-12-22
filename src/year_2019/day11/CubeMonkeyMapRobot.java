package year_2019.day11;

import viewModelUtil.CartesianPoint;
import year_2022.day_22.IMonkeyMapDiagram;
import year_2022.day_22.MonkeyMapRobot;

public class CubeMonkeyMapRobot extends MonkeyMapRobot {

    public CubeMonkeyMapRobot(IMonkeyMapDiagram diagram) {
        super(diagram);
    }

    @Override
    public CartesianPoint previewMoveForward() {
        return diagram.getNextPointInDirection(getPosition(), getFacing());
    }

    @Override
    public void moveForward() {

    }
}
