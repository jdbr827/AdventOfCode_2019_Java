package year_2022.day_14.model;

import viewModelUtil.JavaPoint;

public interface Day14SolutionMethod {

    static Day14SolutionMethod createNew(int solutionMethodId, Day14ModelView modelView, Day14ModelPartConstraint partConstraint) {
        switch (solutionMethodId) {
            case 1:
                return new Day14ModelImpl(modelView, partConstraint);
            case 2:
                return new Day14ModelSolutionMethod2(modelView, partConstraint);
            case 3:
                return new Day14ModelSolutionMethod3(modelView, partConstraint);
            default:
                return null;
        }
    }

    void executeOneTimeStep();

    default int getNumSandPiecesFallenSoFar() {
        return getModelView().dataModel.getNumAtRest();
    }


    default void setCurrentSandPiece(JavaPoint p) {
        getModelView().setCurrentSandPiece(p);
    }

    /**
     *
     * @param sandPiece piece of sand
     * @return new location of the sand piece, or null if it did not move.
     */
    default JavaPoint moveSandPiece(JavaPoint sandPiece) {
        JavaPoint down = new JavaPoint(sandPiece.x, sandPiece.y + 1);
        JavaPoint downLeft = new JavaPoint(sandPiece.x - 1, sandPiece.y + 1);
        JavaPoint downRight = new JavaPoint(sandPiece.x + 1, sandPiece.y + 1);

        if (getPartConstraint().allowsSand(down)) {
            return down;
        } else if (getPartConstraint().allowsSand(downLeft)) {
            return downLeft;
        } else if (getPartConstraint().allowsSand(downRight)) {
            return downRight;
        }
        getModelView().setToAtRest(sandPiece);
        return null;

    }

    Day14ModelPartConstraint getPartConstraint();



    Day14ModelView getModelView();
}
