package year_2022.day_09;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Rope {
    ChessKing head = new ChessKing();
    ChessKing tail = new ChessKing();

    void moveRope(IChessKing.MovementDirection direction) {
        head.move(direction);
        int dx = head.getPosition().x - tail.getPosition().x;
        int dy = head.getPosition().y - tail.getPosition().y;

        if (dx >= 2) {
            tail.move(IChessKing.MovementDirection.RIGHT);
        } if (dx <= -2) {
            tail.move(IChessKing.MovementDirection.LEFT);
        }

        if (dy >= 2) {
            tail.move(IChessKing.MovementDirection.UP);
        } if (dy <= -2) {
            tail.move(IChessKing.MovementDirection.DOWN);
        }
        System.out.println("MOVE: " + direction.name());
        System.out.println("HEAD: " + head.getPosition());
        System.out.println("TAIL: " + tail.getPosition());
        System.out.println("-----");
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
