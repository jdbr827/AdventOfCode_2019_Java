package year_2019.day15;

import year_2019.CartesianPoint;

import java.awt.*;

public abstract class DistanceTracker {
    Color viewColor;
    abstract Integer getDistanceAtCurrentLocation();
    abstract void setDistanceAtCurrentLocation(Integer distance);
    protected abstract Boolean searchIsFinished();

    public DistanceTracker(Color color) {
        setViewColor(color);
    }

        public void setViewColor(Color color) {
            viewColor = color;
        }
}
