package year_2022.day_14.model.dataModel;

import viewModelUtil.JavaPoint;
import year_2022.day_14.model.PointState;

public interface Day14DataReader {
    PointState getStateOfPoint(JavaPoint javaPoint);

    boolean getIsFloor(JavaPoint javaPoint);

    int getLowestRockY();

    boolean getIsAtRest(JavaPoint javaPoint);

    int getNumAtRest();

    boolean getIsRock(JavaPoint javaPoint);

    JavaPoint getCurrentSandPiece();
}
