package year_2022.day_14.model.partConstraints;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import viewModelUtil.JavaPoint;
import year_2022.day_14.model.dataModel.Day14DataModel;

import static year_2022.day_14.model.solutionMethod.Day14SolutionMethod.SPAWN_POINT;


@RequiredArgsConstructor
public class Day14ModelPartConstraint2 implements Day14ModelPartConstraint {

    @NotNull @Getter
    Day14DataModel dataModel;


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
