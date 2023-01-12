package year_2022.day_14.model;

import viewModelUtil.JavaPoint;
import year_2022.day_14.Day14Scanner;

import java.util.Collection;

public interface Day14Model {
    Collection<JavaPoint> getRocks();
    boolean isRock(JavaPoint p);
    boolean isAtRest(JavaPoint p);
    boolean endCondition();
    int getNumSandPiecesFallenSoFar();
    int runModelOnly();
    void executeOneTimeStep();
    Collection<JavaPoint> getCurrentSandPieces();

    static Day14Model fromCornerRocksFile(String fileName, int version) {
        if (version == 1) {
            return new Day14ModelImpl(new Day14Scanner(fileName).readInRocks());
        } if (version == 2) {
            return new Day14ModelImpl2(new Day14Scanner(fileName).readInRocks());
        } if (version == 3) {
            return new Day14ModelImpl3(new Day14Scanner(fileName).readInRocks());
        }
        return new Day14ModelImpl(new Day14Scanner(fileName).readInRocks());
    }

}

