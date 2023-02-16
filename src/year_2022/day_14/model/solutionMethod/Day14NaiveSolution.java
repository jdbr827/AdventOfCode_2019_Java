package year_2022.day_14.model.solutionMethod;

import lombok.Getter;
import viewModelUtil.JavaPoint;
import year_2022.day_14.model.Day14ModelView;
import year_2022.day_14.model.dataModel.Day14DataReader;
import year_2022.day_14.model.partConstraints.Day14ModelPartConstraint;

class Day14NaiveSolution implements Day14SolutionMethod {
    @Getter final Day14ModelView modelView;
    @Getter final Day14ModelPartConstraint partConstraint;
    @Getter final Day14DataReader dataReader;

    public Day14NaiveSolution(Day14ModelView modelView, Day14ModelPartConstraint partConstraint, Day14DataReader dataReader) {
        this.modelView = modelView;
        this.partConstraint = partConstraint;
        this.dataReader = dataReader;
        createNewSandPiece();
    }


    private JavaPoint getCurrentSandPiece() {
        return dataReader.getCurrentSandPiece();
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
