package year_2022.day_14.model;

import lombok.Getter;
import viewModelUtil.JavaPoint;

import java.util.*;

class Day14ModelImpl implements IDay14Model {
    @Getter final Day14ModelView modelView;
    @Getter JavaPoint currentSandPiece;

    @Getter int numSandPiecesFallenSoFar = 0;

    @Getter public static final JavaPoint SPAWN_POINT = new JavaPoint(500, 0);

    public Day14ModelImpl(Day14ModelView modelView, Set<JavaPoint> rocks) {
        this.modelView = modelView;
        this.modelView.setDataModel(new Day14DataModelImpl3(rocks));
        createNewSandPiece();
    }


    @Override
    public Day14DataModel getDataModel() {
        return modelView.dataModel;
    }

    // A piece of sand "falls to the abyss" when there are no more rocks to stop it
    @Override
    public boolean endCondition() {
        return getCurrentSandPiece().y >= getLowestRockY();
    }

    int getLowestRockY() {
        return getDataModel().getLowestRockY();
    }

    @Override
    public void setPieceToRest(JavaPoint sandPiece) {
        modelView.setToAtRest(sandPiece);
        numSandPiecesFallenSoFar++;
    }

    private void moveCurrentSandPiece() {
        JavaPoint previousPoint = new JavaPoint(currentSandPiece.x, currentSandPiece.y);
        JavaPoint newSandPiece = moveSandPiece(currentSandPiece);
        if (newSandPiece == null) {
            createNewSandPiece();
            return;
        }
        currentSandPiece = newSandPiece;
        modelView.setToFalling(currentSandPiece);
        modelView.setToOpen(previousPoint);


    }



    protected void createNewSandPiece() {
        currentSandPiece = SPAWN_POINT;
    }


    @Override
    public void executeOneTimeStep() {
        moveCurrentSandPiece();
    }

    @Override
    public int floorY() {
        return -Integer.MAX_VALUE;
    }


}
