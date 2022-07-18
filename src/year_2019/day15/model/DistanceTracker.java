package year_2019.day15.model;

import java.awt.*;

public abstract class DistanceTracker {
    protected Color viewColor;
    protected abstract Integer getDistanceAtCurrentLocation();
    protected abstract void setDistanceAtCurrentLocation(Integer distance);
    protected abstract Boolean searchIsFinished();

    public DistanceTracker(Color color) {
        setViewColor(color);
    }

        public void setViewColor(Color color) {
            viewColor = color;
        }
}
