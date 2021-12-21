package year_2019.day11;

import year_2019.CartesianColorViewModel;
import year_2019.CartesianPoint;

import static year_2019.day11.HullPaintingRobot.Direction;

import java.awt.*;
import java.util.Map;

public class HullViewModel extends CartesianColorViewModel {
    Day11Hull view;
    Day11 controller;

    public HullViewModel(Day11Hull day11Hull) {
        super();
        this.view = day11Hull;
    }

    public void setController(Day11 controller) {
        this.controller = controller;
    }

    @Override
    public Color getBackgroundColorAtCartesian(Point q) {
        return cartesianColorMap.getOrDefault(q, Color.GRAY);
    }

    @Override
    public Color getForegroundColorAtCartesian(Point q) {
        if (q.equals(controller.getCurrentRobotPosition())) {
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
        setValueAtCartesian(controller.getCurrentRobotPosition(), droidFacingMap.get(controller.getCurrentRobotFacing()));
        view.repaint();
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


    public void paintAtCartesian(CartesianPoint position, Long paint) {
        setColorAtCartesian(position, hullPaintingColorFunction(paint));
        view.repaint();
    }

    public void setNumberOfUniquePanelsPainted(int numberOfUniquePanelsPainted) {
        view.setNumberOfUniquePanelsPainted(numberOfUniquePanelsPainted);
        view.repaint();
    }
}
