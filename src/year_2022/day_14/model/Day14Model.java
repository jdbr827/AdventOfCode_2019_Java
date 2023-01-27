package year_2022.day_14.model;

import viewModelUtil.JavaPoint;
import year_2022.day_14.Day14Scanner;

import java.util.Set;

import static year_2022.day_14.model.Day14Model.fromRockSet;

public interface Day14Model {
    boolean isRock(JavaPoint p);
    boolean isAtRest(JavaPoint p);
    boolean isSandFallingAt(JavaPoint javaPoint);


    boolean endCondition();
    int getNumSandPiecesFallenSoFar();

    void executeOneTimeStep();

    default int runModelOnly() {
        while (!endCondition()) {
            executeOneTimeStep();
        }
        return getNumSandPiecesFallenSoFar();
    }


    static Day14Model fromCornerRocksFile(String fileName, int version, Day14ModelView dataModel) {
        return fromRockSet(new Day14Scanner(fileName).readInRocks(), version, dataModel);
    }

    static Day14Model fromRockSet(Set<JavaPoint> rocks, int version, Day14ModelView modelView) {
        if (version == 1) {
            return new Day14ModelImpl(modelView, rocks);
        } if (version == 2) {
            return new Day14ModelImpl2(modelView, rocks);
        } if (version == 3) {
            return new Day14ModelImpl3(modelView, rocks);
        }
        if (version == 4) {
            return new Day14ModelImpl4(modelView, rocks);
        }
        return new Day14ModelImpl(modelView, rocks);
    }


    default PointState getStateOfPoint(JavaPoint javaPoint) {
        if (isRock(javaPoint)) {
            return PointState.ROCK;
        }
        if (isAtRest(javaPoint)) {
            return PointState.REST;
        }
        if (isSandFallingAt(javaPoint)) {
            return PointState.FALLING;
        }
        return PointState.OPEN;
    }

    int floorY();
}
