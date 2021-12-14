package year_2019;

import javax.swing.*;
import java.awt.*;

public class CartesianViewModel {
    protected Point cartesianOrigin = new Point(0, 0);

    public Point convertCartesianToJava(Point cartesianPoint) {
        return new Point(cartesianOrigin.y - cartesianPoint.y, cartesianPoint.x + cartesianOrigin.x);
    }

    public Point convertJavaToCartesian(Point javaPoint) {
        return new Point(javaPoint.y - cartesianOrigin.y, javaPoint.x - cartesianOrigin.x);
    }



}
