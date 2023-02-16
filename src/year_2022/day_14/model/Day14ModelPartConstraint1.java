package year_2022.day_14.model;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import viewModelUtil.JavaPoint;

@RequiredArgsConstructor
public class Day14ModelPartConstraint1 implements Day14ModelPartConstraint {
    @NotNull Day14DataModel dataModel;

     // A piece of sand "falls to the abyss" when there are no more rocks to stop it
    @Override
    public boolean endCondition() {
        return dataModel.getCurrentSandPiece().y >= dataModel.getLowestRockY();
    }

    @Override
    public int floorY() {
        return -Integer.MAX_VALUE;
    }

    @Override
    public boolean allowsSand(JavaPoint javaPoint) {
        return !dataModel.getIsRock(javaPoint) && !dataModel.getIsAtRest(javaPoint);
    }
}
