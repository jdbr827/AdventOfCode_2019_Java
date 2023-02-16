package year_2022.day_14.model.dataModel;

import viewModelUtil.JavaPoint;
import year_2022.day_14.model.PointState;

// each point always goes from nothing --> falling --> atRest (unless a rock)


/**
 * Keeps track of the state of each point in the grid
 */
public interface Day14DataModel {
    PointState getStateOfPoint(JavaPoint javaPoint);

    boolean getIsFloor(JavaPoint javaPoint);
    int getLowestRockY();
    boolean getIsAtRest(JavaPoint javaPoint);
    void setToFalling(JavaPoint javaPoint);
    void setToAtRest(JavaPoint javaPoint);
    int getNumAtRest();

    default boolean getIsRock(JavaPoint javaPoint) {
        return getStateOfPoint(javaPoint).equals(PointState.ROCK);
    }

    void setCurrentSandPiece(JavaPoint p);
    JavaPoint getCurrentSandPiece();

    void setToOpen(JavaPoint p);
}

