package year_2022.day_09;

import lombok.Setter;
import viewModelUtil.CartesianColorTableModel;
import viewModelUtil.CartesianPoint;

import java.awt.*;

public class Day9ViewModel extends CartesianColorTableModel {
    private Day9View view;

    @Setter
    private Day9Controller controller;

    public Day9ViewModel(Day9View day9View) {
        this.view = day9View;

    }

    @Override
    public Color getBackgroundColorAtCartesian(Point q) {
        return Color.WHITE;
    }

    @Override
    public Color getForegroundColorAtCartesian(Point q) {
        if (q.equals(controller.copyHeadPosition()) || q.equals(controller.copyTailPosition())) {
            return Color.BLACK;
        };
        return getBackgroundColorAtCartesian(q);
    }

    public void updateHead() {
        setValueAtCartesian(controller.copyHeadPosition(), "H");
    }

    public void updateTail() {
        setValueAtCartesian(controller.copyTailPosition(), "T");
    }
}
