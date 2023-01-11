package year_2022.day_09;

import viewModelUtil.CartesianPoint;

import java.awt.*;

public class Day9Controller {
    private final Day9View view;
    Rope myRope = new Rope(3);


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
}
