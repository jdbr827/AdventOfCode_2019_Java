package viewModelUtil;

import java.awt.*;

public class CartesianPoint extends Point {

    public CartesianPoint(int x, int y) {
        super(x, y);
    }

    public static CartesianPoint fromPoint(Point p) {
        return new CartesianPoint(p.x, p.y);
    }

    public CartesianPoint add(Point p) {
        return new CartesianPoint(p.x + x, p.y + y);
    }

    public boolean isInBoundariesInclusive(int xLow, int xHigh, int yLow, int yHigh) {
        return x >= xLow && x <= xHigh && y >= yLow && y <= yHigh;
    }

    public Point toPoint() {
        return new Point(x, y);
    }


}
