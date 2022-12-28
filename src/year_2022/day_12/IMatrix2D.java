package year_2022.day_12;

import viewModelUtil.CartesianPoint;

import java.awt.*;

public interface IMatrix2D<T> {

    T getValue(int x, int y);

    void setValue(int x, int y, T val);

    default T getValue(Point p) {
        return getValue(p.x, p.y);
    }

    int getXSize();

    int getYSize();

    default boolean containsPoint(Point p) {
        return CartesianPoint.fromPoint(p).isInBoundariesInclusive(0, getXSize()-1, 0, getYSize()-1);
    }

}
