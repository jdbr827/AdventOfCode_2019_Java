package year_2022.day_14.model.solutionMethod;

import viewModelUtil.JavaPoint;
import year_2022.day_14.model.Day14ModelView;
import year_2022.day_14.model.dataModel.Day14DataReader;
import year_2022.day_14.model.partConstraints.Day14ModelPartConstraint;

import java.util.Stack;

/**
 * Key insight: If a piece of sand comes to rest at some Y, then the path it takes from y=0 to y=Y-1 is also taken
 * by the next piece of sand. Basically, if the first piece can or can't go in a specific direction, then the second
 * piece also can/can't go there up to y=Y-1 no new pieces came to rest at any of those ys.
 *
 * from y=0 to y=Y-1
 */
public class Day14ModelSolutionMethod3 extends Day14NaiveSolution {

    Stack<JavaPoint> currentFallingPieces = new Stack<>();

    public Day14ModelSolutionMethod3(Day14ModelView modelView, Day14ModelPartConstraint partConstraint, Day14DataReader dataReader) {
        super(modelView, partConstraint, dataReader);
        currentFallingPieces.add(SPAWN_POINT);
    }

    @Override
    public void executeOneTimeStep() {
        JavaPoint leadNext;
        while ((leadNext = moveSandPiece(currentFallingPieces.peek())) == null) {
            currentFallingPieces.pop();
            if (currentFallingPieces.isEmpty()) {
                return;
            }
        }
        modelView.setToFalling(leadNext);
        currentFallingPieces.push(leadNext);
        setCurrentSandPiece(leadNext);
    }
}
