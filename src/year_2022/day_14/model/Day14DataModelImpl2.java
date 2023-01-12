package year_2022.day_14.model;

import viewModelUtil.JavaPoint;
import year_2022.day_14.Day14Controller;

public class Day14DataModelImpl2 extends Day14DataModelImpl {
    Day14Controller controller;


    @Override
    public void setToFalling(JavaPoint javaPoint) {
        super.setToFalling(javaPoint);
        controller.setToFalling(javaPoint);
    }

    @Override
    public void setToAtRest(JavaPoint javaPoint) {
        stateMap.put(javaPoint, PointState.REST);
        controller.setToRest(javaPoint);
    }
}
