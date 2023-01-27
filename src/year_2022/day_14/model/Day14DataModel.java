package year_2022.day_14.model;

import viewModelUtil.JavaPoint;

import java.awt.*;

// each point always goes from nothing --> falling --> atRest (unless a rock)
public interface Day14DataModel {
    boolean getIsFloor(JavaPoint javaPoint);
    boolean getIsAtRest(JavaPoint javaPoint);

    void setToFalling(JavaPoint javaPoint);
    void setToAtRest(JavaPoint javaPoint);
    PointState getStateOfPoint(JavaPoint javaPoint);

    default boolean getIsRock(JavaPoint javaPoint) {
        return getStateOfPoint(javaPoint).equals(PointState.ROCK);
    }
}

