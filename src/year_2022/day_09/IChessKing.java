package year_2022.day_09;


import viewModelUtil.CartesianPoint;

public interface IChessKing {

    enum MovementDirection {
        UP(new CartesianPoint(0, 1)),
        UPRIGHT(new CartesianPoint(1, 1)),
        RIGHT(new CartesianPoint(1, 0)),
        DOWNRIGHT(new CartesianPoint(1, -1)),
        DOWN(new CartesianPoint(0, -1)),
        DOWNLEFT(new CartesianPoint(-1, -1)),
        LEFT(new CartesianPoint(-1, 0)),
        UPLEFT(new CartesianPoint(-1, 1));

        public final CartesianPoint translationVector;
        MovementDirection(CartesianPoint translationVector) {
            this.translationVector = translationVector;
        }

    }

    void move(MovementDirection direction);

    CartesianPoint getPosition();
}
