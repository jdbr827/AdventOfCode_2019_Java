package year_2022.day_14;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import viewModelUtil.JavaPoint;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

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


    public Day14ModelImpl(Set<JavaPoint> rocks) {
        this.rocks = rocks;
        piecesAtRest.addAll(getRocks());
        createNewSandPiece();
        lowestRockY = rocks.stream().max(Comparator.comparing(p -> p.y)).get().y;
    }

    @Override
    public boolean isRock(JavaPoint p) {
        return rocks.contains(p);
    }

    @Override
    public boolean isAtRest(JavaPoint p) {
        return piecesAtRest.contains(p);
    }

    @Override
    public boolean endCondition() {
        return currentSandPiece.y >= lowestRockY;
    }


    @Override
    public boolean moveCurrentSandPiece() {
        JavaPoint down = new JavaPoint(currentSandPiece.x, currentSandPiece.y + 1);
        JavaPoint downLeft = new JavaPoint(currentSandPiece.x - 1, currentSandPiece.y + 1);
        JavaPoint downRight = new JavaPoint(currentSandPiece.x + 1, currentSandPiece.y + 1);

        if (!isAtRest(down)) {
            currentSandPiece = down;
            return true;
        } else if (!isAtRest(downLeft)) {
            currentSandPiece = downLeft;
            return true;
        } else if (!isAtRest(downRight)) {
            currentSandPiece = downRight;
            return true;
        }

        piecesAtRest.add(currentSandPiece);
        numSandPiecesFallenSoFar++;
        //controller.setSandPiecesSoFar(numSandPiecesFallenSoFar);
        createNewSandPiece();
        return false;
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


}
