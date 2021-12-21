package year_2019;

import javax.swing.*;
import java.awt.*;

public class CartesianViewModel {
    protected Point cartesianOrigin = new Point(0, 0);

    public JavaPoint convertCartesianToJava(CartesianPoint cartesianPoint) {
        return new JavaPoint(cartesianOrigin.y - cartesianPoint.y, cartesianPoint.x + cartesianOrigin.x);
    }

    public CartesianPoint convertJavaToCartesian(JavaPoint javaPoint) {
        return new CartesianPoint(javaPoint.y - cartesianOrigin.y, javaPoint.x - cartesianOrigin.x);
    }



}
