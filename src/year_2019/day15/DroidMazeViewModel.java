package year_2019.day15;

import year_2019.CartesianColorViewModel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class DroidMazeViewModel extends CartesianColorViewModel {
    Point droidLocation = new Point(0, 0);
    Map<Point,Boolean> usingOxygenDistance = new HashMap<>();

    public DroidMazeViewModel() {
    }

    public void setValueAtCartesian(Point droidLocation, int distance) {
        Point javaPoint = convertCartesianToJava(droidLocation);
        dtm.setValueAt(distance, javaPoint.x, javaPoint.y);
    }
    @Override
    public Color getBackgroundColorAtCartesian(Point q) {
        Color color = cartesianColorMap.getOrDefault(q, Color.GRAY);
        if (q.equals(droidLocation)) {
            color = Color.PINK;
        }
        return color;
    }

    @Override
    public Color getForegroundColorAtCartesian(Point q) {
        Color color = Color.BLACK;
        if (usingOxygenDistance.getOrDefault(q, false)) {
            color = Color.BLUE;
        }
        return color;
    }


}