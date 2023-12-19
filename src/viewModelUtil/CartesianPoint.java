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

    public boolean equals(CartesianPoint otherPoint) {
        return x == otherPoint.getX() && y == otherPoint.getY();
    }

    public static CartesianPoint convertJavaToCartesian(JavaPoint javaPoint, Point cartesianOrigin) {
        return new CartesianPoint(javaPoint.x - cartesianOrigin.x, cartesianOrigin.y - javaPoint.y);
    }

    public static CartesianPoint convertJavaToCartesian(JavaPoint javaPoint) {
        return convertJavaToCartesian(javaPoint, new Point(0, 0));
    }

    public Point toPoint() {
        return new Point(x, y);
    }


}
