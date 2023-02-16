package year_2022.day_14.model;

import viewModelUtil.JavaPoint;

import java.util.*;
import java.util.stream.Collectors;

/*
Key insight: Once the falling piece of sand reaches a certain Y, the set of points that allow sand
will be unchanged for any y < Y. This means that we can speed up our simulation by assuming a new
piece of sand leaves the spawn point every second, and applying the falling logic for sand in a decreasing Y.
Furthermore, since each moment of falling increases Y by exactly 1 (unless the sand comes to rest),
we can keep a list of falling pieces where List[-i] = the piece of sand falling at y=i.
 */

public class Day14ModelSolutionMethod2 extends Day14ModelImpl {

    List<JavaPoint> currentFallingPieces = new ArrayList<>();

    public Day14ModelSolutionMethod2(Day14ModelView modelView, Day14ModelPartConstraint partConstraint) {
        super(modelView, partConstraint);
        setCurrentSandPiece(SPAWN_POINT);
        currentFallingPieces = new ArrayList<>();
    }

    @Override
    public void executeOneTimeStep() {
        currentFallingPieces.add(SPAWN_POINT);
        currentFallingPieces = currentFallingPieces.stream().map(this::moveSandPiece).collect(Collectors.toList());
        currentFallingPieces = currentFallingPieces.stream().filter(Objects::nonNull).collect(Collectors.toList());

        if (!currentFallingPieces.isEmpty()) {
            setCurrentSandPiece(currentFallingPieces.get(0));
            modelView.setToFalling(currentFallingPieces.get(0));
        }
    }


}
