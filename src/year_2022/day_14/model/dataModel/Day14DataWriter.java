package year_2022.day_14.model.dataModel;

import viewModelUtil.JavaPoint;

public interface Day14DataWriter {
    void setToFalling(JavaPoint javaPoint);

    void setToAtRest(JavaPoint javaPoint);

    void setCurrentSandPiece(JavaPoint p);

    void setToOpen(JavaPoint p);
}
