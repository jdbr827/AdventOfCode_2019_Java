package year_2019.day11;

import year_2019.CartesianColorViewModel;

import java.awt.*;

public class HullViewModel extends CartesianColorViewModel {
    @Override
    public Color getBackgroundColorAtCartesian(Point q) {
        return cartesianColorMap.getOrDefault(q, Color.GRAY);
    }

    @Override
    public Color getForegroundColorAtCartesian(Point q) {
        return getBackgroundColorAtCartesian(q);
    }
}
