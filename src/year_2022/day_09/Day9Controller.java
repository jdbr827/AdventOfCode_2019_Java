package year_2022.day_09;

import viewModelUtil.CartesianPoint;

public class Day9Controller {
    private Day9View view;
    Rope myRope = new Rope();


    public Day9Controller(Day9View day9View) {
        this.view = day9View;
    }

    public void moveRope(IChessKing.MovementDirection direction) {
        myRope.moveRope(direction);
        view.updateHead();
        view.updateTail();
        view.repaint();
    }

    public CartesianPoint copyHeadPosition() {
        return myRope.head.copyPosition();
    }

    public CartesianPoint copyTailPosition() {
        return myRope.tail.copyPosition();
    }




}
