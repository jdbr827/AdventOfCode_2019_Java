package year_2022.day_14.model;

import viewModelUtil.JavaPoint;

import java.util.*;
import java.util.stream.Collectors;

public class Day14ModelImpl3 extends Day14ModelImpl {

    List<JavaPoint> currentFallingPieces;

    public Day14ModelImpl3(Set<JavaPoint> rocks) {
        super(rocks);
    }

    @Override
    public void executeOneTimeStep() {
        currentFallingPieces = currentFallingPieces.stream().map(this::moveSandPiece).collect(Collectors.toList());
        currentFallingPieces = currentFallingPieces.stream().filter(Objects::nonNull).collect(Collectors.toList());
        createNewSandPiece();
    }

    @Override
    public void createNewSandPiece() {
        if (currentFallingPieces == null) {
            currentFallingPieces = new ArrayList<>();
        }
        currentFallingPieces.add(SPAWN_POINT);
    }

    @Override
    public boolean isSandFallingAt(JavaPoint javaPoint) {
        return currentFallingPieces.contains(javaPoint);
    }

    @Override
    public boolean endCondition() {
        return currentFallingPieces.get(0).y >= lowestRockY;
    }
}
