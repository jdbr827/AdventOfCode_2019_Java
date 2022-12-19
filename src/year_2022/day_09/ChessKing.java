package year_2022.day_09;

import viewModelUtil.CartesianPoint;

public class ChessKing implements IChessKing {
    CartesianPoint position;

    ChessKing() {
        position = new CartesianPoint(0, 0);
    }

    @Override
    public void move(MovementDirection direction) {
        position.translate(direction.translationVector.x, direction.translationVector.y);
    }

    @Override
    public CartesianPoint getPosition() {
        return position;
    }

    public static void main(String[] args) {
        ChessKing myKing = new ChessKing();
        myKing.move(MovementDirection.UP);
        myKing.move(MovementDirection.UPLEFT);
        myKing.move(MovementDirection.DOWN);
        System.out.println(myKing.getPosition());
    }
}
