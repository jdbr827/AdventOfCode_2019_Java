package year_2022.day_14.model;

import viewModelUtil.JavaPoint;
import year_2022.day_14.Day14Scanner;

import java.util.Collection;

public interface Day14Model {
    Collection<JavaPoint> getRocks();
    boolean isRock(JavaPoint p);
    JavaPoint getCurrentSandPiece();
    boolean isAtRest(JavaPoint p);
    boolean endCondition();

    /**
     *
     * @return true if we were able to move the sand piece, false if not.
     */
    boolean moveCurrentSandPiece();

    int getSandPiecesSoFar();

    int runModelOnly();

    static Day14Model fromCornerRocksFile(String fileName, int version) {
        if (version == 1) {
            return new Day14ModelImpl(new Day14Scanner(fileName).readInRocks());
        } else {
            return new Day14ModelImpl2(new Day14Scanner(fileName).readInRocks());
        }
    }
}

