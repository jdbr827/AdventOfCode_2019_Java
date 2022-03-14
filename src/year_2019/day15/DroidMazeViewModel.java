package year_2019.day15;

import year_2019.CartesianColorViewModel;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class DroidMazeViewModel extends CartesianColorViewModel {
    Point droidLocation = new Point(0, 0);
    Map<Point,Color> foregroundColor = new HashMap<>();

    public DroidMazeViewModel() {
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
        Color backgroundColor = getBackgroundColorAtCartesian(q);
        return foregroundColor.getOrDefault(q, backgroundColor);
    }


}