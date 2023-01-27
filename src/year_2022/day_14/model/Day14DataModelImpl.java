package year_2022.day_14.model;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import viewModelUtil.JavaPoint;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@RequiredArgsConstructor
public class Day14DataModelImpl implements Day14DataModel {

    Map<JavaPoint, PointState> stateMap = new HashMap<>();
    @NotNull Integer lowestRockY;

    @Override
    public boolean getIsFloor(JavaPoint javaPoint) {
        return javaPoint.y == lowestRockY;
    }

    public boolean getIsAtRest(JavaPoint javaPoint) {
        return stateMap.get(javaPoint).equals(PointState.REST);
    }

    public boolean getIsSandFallingAt(JavaPoint javaPoint) {
        return stateMap.get(javaPoint).equals(PointState.FALLING);
    }

    public boolean getIsRock(JavaPoint javaPoint) {
        return stateMap.get(javaPoint).equals(PointState.ROCK);
    }

    public void setToFalling(JavaPoint javaPoint) {
        stateMap.put(javaPoint, PointState.FALLING);
    }

    public void setToAtRest(JavaPoint javaPoint) {
        stateMap.put(javaPoint, PointState.REST);
    }

}
