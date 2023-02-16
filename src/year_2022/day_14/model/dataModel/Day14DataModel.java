package year_2022.day_14.model.dataModel;

import viewModelUtil.JavaPoint;
import year_2022.day_14.model.PointState;

// each point always goes from nothing --> falling --> atRest (unless a rock)


/**
 * Keeps track of the state of each point in the grid
 */
public interface Day14DataModel extends Day14DataReader {

    void setToFalling(JavaPoint javaPoint);
    void setToAtRest(JavaPoint javaPoint);

    @Override
    default boolean getIsRock(JavaPoint javaPoint) {
        return getStateOfPoint(javaPoint).equals(PointState.ROCK);
    }

    void setCurrentSandPiece(JavaPoint p);

    void setToOpen(JavaPoint p);
}

