package viewModelUtil;

import java.awt.*;

public class JavaPoint extends Point {

     public JavaPoint(int x, int y) {
        super(x, y);
    }

    public static JavaPoint convertDTMPointToJavaPoint(DTMPoint dtmPoint) {
         return new JavaPoint(dtmPoint.y, dtmPoint.x);
    }
}
