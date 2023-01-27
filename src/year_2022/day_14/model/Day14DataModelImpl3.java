package year_2022.day_14.model;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import viewModelUtil.JavaPoint;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Day14DataModelImpl3 implements Day14DataModel {
    @Getter
    @NotNull Set<JavaPoint> rocks;
    Set<JavaPoint> piecesAtRest = new HashSet<JavaPoint>();
    int lowestRockY;
    JavaPoint currentSandPiece;


    public Day14DataModelImpl3(@NotNull Set<JavaPoint> rocks) {
        this.rocks = rocks;
        lowestRockY = rocks.stream().max(Comparator.comparing(p -> p.y)).get().y;
    }

    @Override
    public boolean getIsAtRest(JavaPoint javaPoint) {
        return piecesAtRest.contains(javaPoint);
    }

    @Override
    public boolean getIsSandFallingAt(JavaPoint javaPoint) {
        return javaPoint.equals(currentSandPiece);
    }


    @Override
    public boolean getIsRock(JavaPoint javaPoint) {
        return rocks.contains(javaPoint);
    }

    @Override
    public void setToFalling(JavaPoint javaPoint) {
        currentSandPiece = javaPoint;
    }

    @Override
    public void setToAtRest(JavaPoint javaPoint) {
        piecesAtRest.add(javaPoint);
    }

    @Override
    public PointState getStateOfPoint(JavaPoint javaPoint) {
        if (getIsRock(javaPoint)) {
            return PointState.ROCK;
        }
        if (getIsAtRest(javaPoint)) {
            return PointState.REST;
        }
        if (getIsSandFallingAt(javaPoint)) {
            return PointState.FALLING;
        }
        return PointState.OPEN;
    }

    @Override
    public boolean getIsFloor(JavaPoint javaPoint) {
        return javaPoint.y == lowestRockY + 2;
    }
}