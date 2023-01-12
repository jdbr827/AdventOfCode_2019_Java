package year_2022.day_14.view;

import viewModelUtil.JavaColorTableModel;
import viewModelUtil.JavaPoint;
import year_2022.day_14.Day14Controller;

import java.awt.*;

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

    @Override
    public Color getBackgroundColorAtJava(JavaPoint javaPoint) {
        if (controller.isRock(javaPoint)) { return  Color.GRAY;}
        if (controller.isFalling(javaPoint)) {return Color.CYAN;}
        if (controller.isAtRest(javaPoint)) { return Color.ORANGE; }

        return Color.WHITE;

    }
}
