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
        if (controller.tailVisited(q)) {
            return Color.BLUE;
        };
        return Color.WHITE;
    }

    @Override
    public Color getForegroundColorAtCartesian(Point q) {
        Rope ptr = controller.myRope;
        while (ptr != null) {
            if (q.equals(ptr.head.copyPosition())) {
                return Color.BLACK;
            }
            ptr = ptr.tail;
        }
        return getBackgroundColorAtCartesian(q);
    }

    public void updateRope() {
        Rope ptr = controller.myRope;
        while (ptr != null) {
           setValueAtCartesian(ptr.head.copyPosition(), ptr.knotName);
            ptr = ptr.tail;
        }
    }
}
