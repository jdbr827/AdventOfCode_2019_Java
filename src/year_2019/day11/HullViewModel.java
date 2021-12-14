package year_2019.day11;

import year_2019.CartesianColorViewModel;

import java.awt.*;

public class HullViewModel extends CartesianColorViewModel {
    HullPaintingRobot droid;

    public void setDroid(HullPaintingRobot droid) {
        this.droid = droid;
    }

    @Override
    public Color getBackgroundColorAtCartesian(Point q) {
        return cartesianColorMap.getOrDefault(q, Color.GRAY);
    }

    @Override
    public Color getForegroundColorAtCartesian(Point q) {
        if (droid != null && q.equals(droid.position)) {
            return Color.BLACK;
        } else {
            return getBackgroundColorAtCartesian(q);
        }
    }
}
