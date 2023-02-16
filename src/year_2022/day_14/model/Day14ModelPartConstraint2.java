package year_2022.day_14.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import viewModelUtil.JavaPoint;

import static year_2022.day_14.model.Day14ModelImpl.SPAWN_POINT;

@RequiredArgsConstructor
public class Day14ModelPartConstraint2 implements Day14ModelPartConstraint {

    @NotNull @Getter Day14DataModel dataModel;


    @Override
    public boolean endCondition() {
        return !allowsSand(SPAWN_POINT);
    }

    @Override
    public int floorY() {
        return dataModel.getLowestRockY() + 2;
    }

    @Override
    public boolean allowsSand(JavaPoint javaPoint) {
        return !dataModel.getIsRock(javaPoint) && !dataModel.getIsAtRest(javaPoint) && !dataModel.getIsFloor(javaPoint);
    }
}
