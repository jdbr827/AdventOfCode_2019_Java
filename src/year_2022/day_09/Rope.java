package year_2022.day_09;

import viewModelUtil.CartesianPoint;

import java.util.HashMap;
import java.util.Map;

public class Rope {
    ChessKing head = new ChessKing();
    Rope tail;
    String knotName;

    Map<CartesianPoint, Boolean> visited = new HashMap<>();

    int uniqueVisited = 0;

    Rope(int numTail) {
        this(numTail, 0);
    }


    Rope(int numTail, int depth) {

        if (numTail == 1) {
            tail = null;
            knotName = "T";
            visited.put(head.copyPosition(), true);
            uniqueVisited++;
        } else {
            knotName = depth == 0 ? "H" : String.valueOf(depth);
            tail = new Rope(numTail - 1, depth + 1);
        }

    }

    int numTailVisited() {
        if (tail != null) {
            return tail.numTailVisited();
        }
        return uniqueVisited;
    }

    boolean tailVisited(CartesianPoint point) {
        if (tail != null) {
            return tail.tailVisited(point);
        }
        return visited.getOrDefault(point, false);
    }


    void moveRope(IChessKing.MovementDirection direction) {
        head.move(direction);
        if (tail != null) {
            int dx = head.getPosition().x - tail.head.getPosition().x;
            int dy = head.getPosition().y - tail.head.getPosition().y;

            if (dx >= 2) {
                if (dy > 0) {
                    tail.moveRope(IChessKing.MovementDirection.UPRIGHT);
                } else if (dy < 0) {
                    tail.moveRope(IChessKing.MovementDirection.DOWNRIGHT);
                } else {
                    tail.moveRope(IChessKing.MovementDirection.RIGHT);
                }
            } else if (dx <= -2) {
                if (dy > 0) {
                    tail.moveRope(IChessKing.MovementDirection.UPLEFT);
                } else if (dy < 0) {
                    tail.moveRope(IChessKing.MovementDirection.DOWNLEFT);
                } else {
                    tail.moveRope(IChessKing.MovementDirection.LEFT);
                }

            } else if (dy >= 2) {
                if (dx > 0) {
                    tail.moveRope(IChessKing.MovementDirection.UPRIGHT);
                } else if (dx < 0) {
                    tail.moveRope(IChessKing.MovementDirection.UPLEFT);
                } else {
                    tail.moveRope(IChessKing.MovementDirection.UP);
                }
            } else if (dy <= -2) {
                if (dx > 0) {
                    tail.moveRope(IChessKing.MovementDirection.DOWNRIGHT);
                } else if (dx < 0) {
                    tail.moveRope(IChessKing.MovementDirection.DOWNLEFT);
                } else {
                    tail.moveRope(IChessKing.MovementDirection.DOWN);
                }
            }
        } else {
            if (!visited.getOrDefault(head.copyPosition(), false)) {
                uniqueVisited++;
                visited.put(head.copyPosition(), true);
            }
        }
    }

    public static void main(String[] args) {
        Rope myRope = new Rope(2);
        myRope.moveRope(IChessKing.MovementDirection.UP);
        myRope.moveRope(IChessKing.MovementDirection.UP);
        myRope.moveRope(IChessKing.MovementDirection.UPRIGHT);
        myRope.moveRope(IChessKing.MovementDirection.RIGHT);
        myRope.moveRope(IChessKing.MovementDirection.UPRIGHT);
        myRope.moveRope(IChessKing.MovementDirection.DOWNLEFT);
    }
}
