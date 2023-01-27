package year_2022.day_14.view;

import viewModelUtil.JavaColorTableModel;
import viewModelUtil.JavaPoint;
import year_2022.day_14.Day14Controller;
import year_2022.day_14.IDay14Controller;
import year_2022.day_14.model.PointState;

import java.awt.*;
import java.util.Map;

public class Day14ViewModel extends JavaColorTableModel {
    Day14View view;
    IDay14Controller controller;
    int largestX = 0;

    @Override
    public void setValueAtJava(JavaPoint javaPoint, Object value) {
        super.setValueAtJava(javaPoint, value);
        if (largestX <= javaPoint.x && view.table1 != null) {
            view.resizeTable();
            largestX = javaPoint.x;
        }
    }

    public Day14ViewModel(Day14View day14View) {
        view = day14View;
        controller = view.controller;
        setValueAtJava(new JavaPoint(500, 0), "+");
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
