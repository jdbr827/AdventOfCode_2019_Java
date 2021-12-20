package year_2019.day11;

import year_2019.CartesianColorViewModel;
import static year_2019.day11.HullPaintingRobot.Direction;

import java.awt.*;
import java.util.Map;

public class HullViewModel extends CartesianColorViewModel {
    HullPainterModel model;

    public void setModel(HullPainterModel model) {
        this.model = model;
    }

    @Override
    public Color getBackgroundColorAtCartesian(Point q) {
        return cartesianColorMap.getOrDefault(q, Color.GRAY);
    }

    @Override
    public Color getForegroundColorAtCartesian(Point q) {
        if (q.equals(model.getCurrentRobotPosition())) {
            return Color.BLACK;
        } else {
            return getBackgroundColorAtCartesian(q);
        }
    }

    public static Map<Direction, Character> droidFacingMap = Map.of(
        Direction.UP, '^',
        Direction.DOWN, 'v',
        Direction.LEFT, '<',
        Direction.RIGHT, '>'
    );

    public void updateRobot() {
        setValueAtCartesian(model.getCurrentRobotPosition(), droidFacingMap.get(model.getCurrentRobotFacing()));
    }

    public static <T> Color hullPaintingColorFunction(T value) {
        if (value != null) {
            if (value.equals(1L)) {
                return Color.WHITE;
            } else {
                return Color.GRAY;
            }
        } else {
            return Color.GRAY;
        }
    }


    public void paintAtCartesian(Point position, Long paint) {
        setColorAtCartesian(position, hullPaintingColorFunction(paint));
    }
}
