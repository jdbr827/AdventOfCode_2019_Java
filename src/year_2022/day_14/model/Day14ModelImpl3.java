package year_2022.day_14.model;

import viewModelUtil.JavaPoint;

import java.util.*;
import java.util.stream.Collectors;

public class Day14ModelImpl3 extends Day14ModelImpl {

    List<JavaPoint> currentSandPieces;

    public Day14ModelImpl3(Set<JavaPoint> rocks) {
        super(rocks);
    }

    @Override
    public void executeOneTimeStep() {
        currentSandPieces = currentSandPieces.stream().map(this::moveSandPiece).collect(Collectors.toList());
        currentSandPieces = currentSandPieces.stream().filter(Objects::nonNull).collect(Collectors.toList());
        createNewSandPiece();
    }

    @Override
    public void createNewSandPiece() {
        if (currentSandPieces == null) {
            currentSandPieces = new ArrayList<>();
        }
        currentSandPieces.add(SPAWN_POINT);
    }

    @Override
    public List<JavaPoint> getCurrentSandPieces() {
        return currentSandPieces;
    }

    @Override
    public boolean endCondition() {
        return currentSandPieces.get(0).y >= lowestRockY;
    }
}
