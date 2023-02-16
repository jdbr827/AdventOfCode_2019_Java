package year_2022.day_14.model.dataModel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import viewModelUtil.JavaPoint;
import year_2022.day_14.model.PointState;
import year_2022.day_14.model.dataModel.Day14DataModel;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@NoArgsConstructor
@RequiredArgsConstructor
public class Day14DataModelImpl implements Day14DataModel {

    @Getter int numAtRest = 0;

    @Override
    public void setToOpen(JavaPoint p) {
        stateMap.put(p, PointState.OPEN);
    }


    @Getter
    @Setter
    JavaPoint currentSandPiece;

    Map<JavaPoint, PointState> stateMap = new HashMap<>();
    @NotNull Integer lowestRockY;

    public Day14DataModelImpl(@NotNull Set<JavaPoint> rocks) {
        rocks.forEach((rock) -> stateMap.put(rock, PointState.ROCK));
        lowestRockY = rocks.stream().max(Comparator.comparing(p -> p.y)).get().y;
    }

    @Override
    public int getLowestRockY() {
        return lowestRockY;
    }

    @Override
    public boolean getIsFloor(JavaPoint javaPoint) {
        return javaPoint.y == lowestRockY + 2;
    }

    public boolean getIsAtRest(JavaPoint javaPoint) {
        return stateMap.getOrDefault(javaPoint, PointState.OPEN).equals(PointState.REST);
    }

    public boolean getIsRock(JavaPoint javaPoint) {
        return stateMap.getOrDefault(javaPoint, PointState.OPEN).equals(PointState.ROCK) || getIsFloor(javaPoint);
    }

    public void setToFalling(JavaPoint javaPoint) {
        stateMap.put(javaPoint, PointState.FALLING);
    }

    public void setToAtRest(JavaPoint javaPoint) {
        stateMap.put(javaPoint, PointState.REST);
        numAtRest++;
    }

    @Override
    public PointState getStateOfPoint(JavaPoint javaPoint) {
        return stateMap.getOrDefault(javaPoint, PointState.OPEN);
    }

}
