package year_2022.day_14;

import viewModelUtil.CartesianColorTableModel;
import viewModelUtil.CartesianPoint;
import viewModelUtil.JavaColorTableModel;
import viewModelUtil.JavaPoint;

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
        return getBackgroundColorAtJava(javaPoint);
    }

    @Override
    public Color getBackgroundColorAtJava(JavaPoint javaPoint) {
        return (controller.isRock(javaPoint)) ? Color.GRAY : Color.WHITE;
    }
}
