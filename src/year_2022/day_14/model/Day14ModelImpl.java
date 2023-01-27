package year_2022.day_14.model;

import lombok.Getter;
import viewModelUtil.JavaPoint;

import java.util.*;

class Day14ModelImpl implements Day14Model {
    final Day14DataModel day14DataModel;
    @Getter JavaPoint currentSandPiece;
    int lowestRockY;

    @Getter int numSandPiecesFallenSoFar = 0;

    public static final JavaPoint SPAWN_POINT = new JavaPoint(500, 0);

    public Day14ModelImpl(Set<JavaPoint> rocks) {
        lowestRockY = rocks.stream().max(Comparator.comparing(p -> p.y)).get().y;
        this.day14DataModel = new Day14DataModelImpl3(rocks);
        createNewSandPiece();
    }

    @Override
    public boolean isRock(JavaPoint p) {
        return day14DataModel.getIsRock(p);
    }

    @Override
    public boolean isAtRest(JavaPoint p) {
        return day14DataModel.getIsAtRest(p) || isRock(p);
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
        setPieceToRest(sandPiece);
        return null;

    }

    private void setPieceToRest(JavaPoint sandPiece) {
        day14DataModel.setToAtRest(sandPiece);
        numSandPiecesFallenSoFar++;
    }


    public void moveCurrentSandPiece() {
        JavaPoint newSandPiece = moveSandPiece(currentSandPiece);
        if (newSandPiece == null) {
            createNewSandPiece();
            return;
        }
        day14DataModel.setToFalling(currentSandPiece);

        currentSandPiece = newSandPiece;
    }



    protected void createNewSandPiece() {
        currentSandPiece = new JavaPoint(500, 0);
    }


    @Override
    public void executeOneTimeStep() {
        moveCurrentSandPiece();
    }

    @Override
    public int floorY() {
        return lowestRockY;
    }

    @Override
    public boolean isSandFallingAt(JavaPoint javaPoint) {
        return javaPoint.equals(currentSandPiece);
    }


}
