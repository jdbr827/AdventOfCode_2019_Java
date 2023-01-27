package year_2022.day_14.view;

import viewModelUtil.JavaColorTableModel;
import viewModelUtil.JavaPoint;
import year_2022.day_14.Day14Controller;
import year_2022.day_14.model.PointState;

import java.awt.*;
import java.util.Map;

public class Day14ViewModel extends JavaColorTableModel {
    Day14View view;
    Day14Controller controller;

    public Day14ViewModel(Day14View day14View) {
        view = day14View;
        controller = view.controller;
        setValueAtJava(new JavaPoint(500, 0), "+");
    }

    @Override
    public Color getForegroundColorAtJava(JavaPoint javaPoint) {
        return Color.BLACK;
    }

    static Map<PointState, Color> colorCode = Map.of(
            PointState.OPEN, Color.WHITE,
            PointState.FALLING, Color.CYAN,
            PointState.REST, Color.ORANGE,
            PointState.ROCK, Color.GRAY
    );

    @Override
    public Color getBackgroundColorAtJava(JavaPoint javaPoint) {
        return colorCode.get(controller.getStateOfPoint(javaPoint));

    }
}
