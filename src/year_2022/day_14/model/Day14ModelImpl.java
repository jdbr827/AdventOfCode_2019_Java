package year_2022.day_14.model;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import viewModelUtil.JavaPoint;

import java.util.*;

class Day14ModelImpl implements Day14Model {
    @Getter
    @NotNull
    Set<JavaPoint> rocks;
    @Getter
    JavaPoint currentSandPiece;

    Set<JavaPoint> piecesAtRest = new HashSet<>();
    int lowestRockY;

    @Getter
    int numSandPiecesFallenSoFar = 0;

    public static final JavaPoint SPAWN_POINT = new JavaPoint(500, 0);

    public Day14ModelImpl(Set<JavaPoint> rocks) {
        this.rocks = rocks;
        createNewSandPiece();
        lowestRockY = rocks.stream().max(Comparator.comparing(p -> p.y)).get().y;
    }

    @Override
    public boolean isRock(JavaPoint p) {
        return rocks.contains(p);
    }

    @Override
    public boolean isAtRest(JavaPoint p) {
        return piecesAtRest.contains(p) || isRock(p);
    }

    @Override
    public boolean endCondition() {
        return currentSandPiece.y >= lowestRockY;
    }

    /**
     *
     * @param sandPiece piece of sand
     * @return new location of the sand piece, or null if it did not move.
     */
    public JavaPoint moveSandPiece(JavaPoint sandPiece) {
        JavaPoint down = new JavaPoint(sandPiece.x, sandPiece.y + 1);
        JavaPoint downLeft = new JavaPoint(sandPiece.x - 1, sandPiece.y + 1);
        JavaPoint downRight = new JavaPoint(sandPiece.x + 1, sandPiece.y + 1);

        if (!isAtRest(down)) {
            return down;
        } else if (!isAtRest(downLeft)) {
            return downLeft;
        } else if (!isAtRest(downRight)) {
            return downRight;
        }
        piecesAtRest.add(sandPiece);
        numSandPiecesFallenSoFar++;
        return null;

    };


    public void moveCurrentSandPiece() {
        JavaPoint newSandPiece = moveSandPiece(currentSandPiece);
        if (newSandPiece == null) {
            createNewSandPiece();
            return;
        }

        currentSandPiece = newSandPiece;
    }

    @Override
    public int getSandPiecesSoFar() {
        return getNumSandPiecesFallenSoFar();
    }


    public void createNewSandPiece() {
        currentSandPiece = new JavaPoint(500, 0);
    }

    public int runModelOnly() {
        while (!endCondition()) {
            moveCurrentSandPiece();
        }
        return getNumSandPiecesFallenSoFar();
    }

    @Override
    public void executeOneTimeStep() {
        moveCurrentSandPiece();
    }

    @Override
    public Collection<JavaPoint> getCurrentSandPieces() {
        return List.of(currentSandPiece);
    }


}
