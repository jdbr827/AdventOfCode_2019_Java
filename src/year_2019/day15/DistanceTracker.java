package year_2019.day15;

import year_2019.CartesianPoint;

public interface DistanceTracker {
    Integer getDistanceAtCurrentLocation();
    void setDistanceAtCurrentLocation(Integer distance);
}
