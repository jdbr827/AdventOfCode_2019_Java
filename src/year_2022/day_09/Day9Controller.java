package year_2022.day_09;

import org.jetbrains.annotations.NotNull;
import viewModelUtil.CartesianPoint;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public class Day9Controller {
    private final Day9View view;
    Rope myRope = new Rope(10);


    public Day9Controller(Day9View day9View) {
        this.view = day9View;
    }

    public void moveRope(IChessKing.MovementDirection direction) {
        myRope.moveRope(direction);
        view.updateRope();
        view.repaint();
    }

    public boolean tailVisited(Point q) {
        return myRope.tailVisited(CartesianPoint.fromPoint(q));
    }

    public boolean ropeContainsPoint(Point q) {
        Rope ptr = myRope;
        while (ptr != null) {
            if (q.equals(ptr.head.copyPosition())) {
                return true;
            }
            ptr = ptr.tail;
        }
        return false;
    }

    public List<Rope> ropePoints() {
        Rope ptr = myRope;
        List<Rope> lst = new ArrayList<>();
        while (ptr != null) {
            lst.add(ptr);
            ptr = ptr.tail;
        }
        return lst;
    }
}
