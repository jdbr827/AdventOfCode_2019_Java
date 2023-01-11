package year_2022.day_14;

import viewModelUtil.CartesianColorTableModel;
import viewModelUtil.CartesianPoint;

import java.awt.*;

public class Day14ViewModel extends CartesianColorTableModel {

    public Day14ViewModel(Day14View day14View) {
        setValueAtCartesian(new CartesianPoint(500, 0), "+");
    }

    @Override
    public Color getBackgroundColorAtCartesian(Point q) {
        return Color.WHITE;
    }

    @Override
    public Color getForegroundColorAtCartesian(Point q) {
        return null;
    }

}
