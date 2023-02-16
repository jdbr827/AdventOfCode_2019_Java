package year_2022.day_14.model;

import viewModelUtil.JavaPoint;

public interface IDay14Model extends Day14Model {


    boolean allowsSand(JavaPoint javaPoint);

    /**
     *
     * @param sandPiece piece of sand
     * @return new location of the sand piece, or null if it did not move.
     */
    default JavaPoint moveSandPiece(JavaPoint sandPiece) {
        JavaPoint down = new JavaPoint(sandPiece.x, sandPiece.y + 1);
        JavaPoint downLeft = new JavaPoint(sandPiece.x - 1, sandPiece.y + 1);
        JavaPoint downRight = new JavaPoint(sandPiece.x + 1, sandPiece.y + 1);

        if (allowsSand(down)) {
            return down;
        } else if (allowsSand(downLeft)) {
            return downLeft;
        } else if (allowsSand(downRight)) {
            return downRight;
        }
        setPieceToRest(sandPiece);
        return null;

    }

    void setPieceToRest(JavaPoint sandPiece);

    Day14ModelView getModelView();
}
