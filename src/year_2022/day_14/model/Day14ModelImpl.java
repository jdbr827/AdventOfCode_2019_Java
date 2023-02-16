package year_2022.day_14.model;

import lombok.Getter;
import viewModelUtil.JavaPoint;

import java.awt.*;
import java.util.*;

class Day14ModelImpl implements Day14SolutionMethod {
    @Getter final Day14ModelView modelView;
    @Getter Day14ModelPartConstraint partConstraint;

    @Getter public static final JavaPoint SPAWN_POINT = new JavaPoint(500, 0);

    public Day14ModelImpl(Day14ModelView modelView, Day14ModelPartConstraint partConstraint) {
        this.modelView = modelView;
        this.partConstraint = partConstraint;
        createNewSandPiece();
    }


    public Day14DataModel getDataModel() {
        return modelView.dataModel;
    }



    JavaPoint getCurrentSandPiece() {
        return modelView.dataModel.getCurrentSandPiece();
    }


    private void moveCurrentSandPiece() {
        JavaPoint previousPoint = new JavaPoint(getCurrentSandPiece().x, getCurrentSandPiece().y);
        JavaPoint newSandPiece = moveSandPiece(getCurrentSandPiece());
        if (newSandPiece == null) {
            createNewSandPiece();
            return;
        }
        setCurrentSandPiece(newSandPiece);
        modelView.setToFalling(getCurrentSandPiece());
        modelView.setToOpen(previousPoint);


    }



    protected void createNewSandPiece() {
        setCurrentSandPiece(SPAWN_POINT);
    }


    @Override
    public void executeOneTimeStep() {
        moveCurrentSandPiece();
    }


}
