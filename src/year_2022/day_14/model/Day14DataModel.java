package year_2022.day_14.model;

import viewModelUtil.JavaPoint;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

// each point always goes from nothing --> falling --> atRest (unless a rock)
public interface Day14DataModel extends Day14DataModel2 {
    boolean getIsAtRest(JavaPoint javaPoint);
    boolean getIsFalling(JavaPoint javaPoint);
    boolean getIsRock(JavaPoint javaPoint);
    void setToFalling(JavaPoint javaPoint);
    void setToAtRest(JavaPoint javaPoint);
}

