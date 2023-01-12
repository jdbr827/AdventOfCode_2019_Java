package year_2022.day_14.model;

import viewModelUtil.JavaPoint;

import java.util.Set;

public class Day14ModelImpl4 extends Day14ModelImpl3 {
    private final Day14DataModel dataModel;
    public Day14ModelImpl4(Set<JavaPoint> rocks, Day14DataModel dataModel) {
        super(rocks);
        this.dataModel = dataModel;
    }

    @Override
    public void executeOneTimeStep() {
        JavaPoint leadNext;
        while ((leadNext = moveSandPiece(currentFallingPieces.get(0))) == null) {
            dataModel.setToAtRest(currentFallingPieces.remove(0));
        }
        dataModel.setToFalling(leadNext);
        currentFallingPieces.add(0, leadNext);
    }
}
