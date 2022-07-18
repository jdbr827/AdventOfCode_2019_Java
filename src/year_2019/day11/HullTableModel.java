package year_2019.day11;

import viewModelUtil.CartesianColorTableModel;
import viewModelUtil.CartesianPoint;
import year_2019.day15.model.CardinalDirection;


import java.awt.*;
import java.util.Map;

public class HullTableModel extends CartesianColorTableModel {
    HullView view;
    Day11 controller;

    public HullTableModel(HullView day11Hull) {
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

    public static Map<CardinalDirection, Character> droidFacingMap = Map.of(
        CardinalDirection.NORTH, '^',
        CardinalDirection.SOUTH, 'v',
        CardinalDirection.WEST, '<',
        CardinalDirection.EAST, '>'
    );

    public void updateRobot() {
        setValueAtCartesian(controller.getCurrentRobotPosition(), droidFacingMap.get(controller.getCurrentRobotFacing()));
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
    }
}
