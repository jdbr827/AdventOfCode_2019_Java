package year_2022.day_14.model;

import viewModelUtil.JavaPoint;

import java.util.Set;

public class Day14ModelImpl4 extends Day14ModelImpl3 {
    public Day14ModelImpl4(Set<JavaPoint> rocks) {
        super(rocks);
    }

    @Override
    public void executeOneTimeStep() {

        JavaPoint leadNext;
        while ((leadNext = moveSandPiece(currentSandPieces.get(0))) == null) {
            currentSandPieces.remove(0);
        }
        currentSandPieces.add(0, leadNext);
    }
}
