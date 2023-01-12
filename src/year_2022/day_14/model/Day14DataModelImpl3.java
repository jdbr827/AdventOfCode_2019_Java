package year_2022.day_14.model;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import viewModelUtil.JavaPoint;

import java.util.HashSet;
import java.util.Set;

public class Day14DataModelImpl3 implements Day14DataModel2 {
    @Getter
    @NotNull Set<JavaPoint> rocks;
    Set<JavaPoint> piecesAtRest = new HashSet<JavaPoint>();


    public Day14DataModelImpl3(Set<JavaPoint> rocks) {
    }

    @Override
    public boolean getIsAtRest(JavaPoint javaPoint) {
        return piecesAtRest.contains(javaPoint);
    }

    @Override
    public boolean getIsRock(JavaPoint javaPoint) {
        return false;
    }

    @Override
    public void setToAtRest(JavaPoint javaPoint) {
        piecesAtRest.add(javaPoint);
    }
}