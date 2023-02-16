package year_2022.day_14.model.solutionMethod;

import viewModelUtil.JavaPoint;
import year_2022.day_14.model.Day14ModelView;
import year_2022.day_14.model.dataModel.Day14DataReader;
import year_2022.day_14.model.partConstraints.Day14ModelPartConstraint;

public interface Day14SolutionMethod {

    Day14ModelPartConstraint getPartConstraint();
    Day14ModelView getModelView();
    Day14DataReader getDataReader();

    void executeOneTimeStep();

    JavaPoint SPAWN_POINT = new JavaPoint(500, 0);

    static Day14SolutionMethod createNew(int solutionMethodId, Day14ModelView modelView, Day14ModelPartConstraint partConstraint, Day14DataReader dataReader) {
        switch (solutionMethodId) {
            case 1:
                return new Day14NaiveSolution(modelView, partConstraint, dataReader);
            case 2:
                return new Day14ModelSolutionMethod2(modelView, partConstraint, dataReader);
            case 3:
                return new Day14ModelSolutionMethod3(modelView, partConstraint, dataReader);
            default:
                return null;
        }
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


}
