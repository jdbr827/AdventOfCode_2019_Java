package year_2022.day_14;

import viewModelUtil.JavaPoint;
import year_2022.day_14.model.PointState;

public interface IDay14Controller {
    PointState getStateOfPoint(JavaPoint javaPoint);

    void autoPilot() throws InterruptedException;

    int getSandPiecesSoFar();


    void setToFalling(JavaPoint javaPoint);

    void setToRest(JavaPoint javaPoint);
}

