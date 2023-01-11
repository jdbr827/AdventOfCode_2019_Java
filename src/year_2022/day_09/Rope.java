package year_2022.day_09;

import lombok.NoArgsConstructor;
import viewModelUtil.CartesianPoint;

import java.util.HashMap;
import java.util.Map;

public class Rope {
    ChessKing head = new ChessKing();
    ChessKing tail = new ChessKing();
    Map<CartesianPoint, Boolean> visited = new HashMap<>();
    int uniqueVisited = 0;

    Rope() {
        visited.put(tail.copyPosition(), true);
        uniqueVisited++;
    }

    int numVisited() {
        return uniqueVisited;
    }

    void moveRope(IChessKing.MovementDirection direction) {
        head.move(direction);
        int dx = head.getPosition().x - tail.getPosition().x;
        int dy = head.getPosition().y - tail.getPosition().y;

        // TODO: FIX
        if (dx >= 2) {
            if (dy > 0) {
                tail.move(IChessKing.MovementDirection.UPRIGHT);
            }
            else if (dy < 0) {
                tail.move(IChessKing.MovementDirection.DOWNRIGHT);
            }
            else {
                tail.move(IChessKing.MovementDirection.RIGHT);
            }
        } else if (dx <= -2) {
            tail.move(IChessKing.MovementDirection.LEFT);
            if (dy > 0) {
                tail.move(IChessKing.MovementDirection.UP);
            }
            if (dy < 0) {
                tail.move(IChessKing.MovementDirection.DOWN);
            }

        } else if (dy >= 2) {
            tail.move(IChessKing.MovementDirection.UP);
            if (dx > 0) {
                tail.move(IChessKing.MovementDirection.RIGHT);
            }
            if (dx < 0) {
                tail.move(IChessKing.MovementDirection.LEFT);
            }
        } else if (dy <= -2) {
            tail.move(IChessKing.MovementDirection.DOWN);
            if (dx > 0) {
                tail.move(IChessKing.MovementDirection.RIGHT);
            }
            if (dx < 0) {
                tail.move(IChessKing.MovementDirection.LEFT);
            }
        }
//        System.out.println("MOVE: " + direction.name());
//        System.out.println("HEAD: " + head.getPosition());
//        System.out.println("TAIL: " + tail.getPosition());
        if (!visited.getOrDefault(tail.copyPosition(), false)) {
            uniqueVisited++;
            visited.put(tail.copyPosition(), true);
        }
        //System.out.println("-----");
    }

    public static void main(String[] args) {
        Rope myRope = new Rope();
        myRope.moveRope(IChessKing.MovementDirection.UP);
        myRope.moveRope(IChessKing.MovementDirection.UP);
        myRope.moveRope(IChessKing.MovementDirection.UPRIGHT);
        myRope.moveRope(IChessKing.MovementDirection.RIGHT);
        myRope.moveRope(IChessKing.MovementDirection.UPRIGHT);
        myRope.moveRope(IChessKing.MovementDirection.DOWNLEFT);
    }
}
