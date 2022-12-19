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

    public CartesianPoint copyPosition() {
        return new CartesianPoint(position.x, position.y);
    }
}
