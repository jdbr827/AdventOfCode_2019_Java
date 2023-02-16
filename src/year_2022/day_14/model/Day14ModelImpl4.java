package year_2022.day_14.model;

import viewModelUtil.JavaPoint;

import java.util.Set;

public class Day14ModelImpl4 extends Day14ModelImpl3 {
    public Day14ModelImpl4(Day14ModelView modelView, Set<JavaPoint> rocks) {
        super(modelView, rocks);
        modelView.setDataModel(new Day14DataModelImpl(rocks));
    }

    @Override
    public void executeOneTimeStep() {
        JavaPoint leadNext;
        while ((leadNext = moveSandPiece(currentFallingPieces.get(0))) == null) {
            modelView.setToAtRest(currentFallingPieces.remove(0));
        }
        modelView.setToFalling(leadNext);
        currentFallingPieces.add(0, leadNext);
    }
}
