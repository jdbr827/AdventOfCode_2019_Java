package year_2022.day_14;

import lombok.Getter;
import viewModelUtil.JavaPoint;

import java.util.Collection;
import java.util.Set;

public interface Day14Model {
    Collection<JavaPoint> getRocks();
    boolean isRock(JavaPoint p);
    Day14Controller getController();
    JavaPoint getCurrentSandPiece();

    /**
     *
     * @return true if we were able to move the sand piece, false if not.
     */
    boolean moveCurrentSandPiece();

    void createNewSandPiece();
}

class Day14ModelImpl implements Day14Model {
    @Getter
    Set<JavaPoint> rocks;
    @Getter
    Day14Controller controller;
    @Getter
    JavaPoint currentSandPiece;


    public Day14ModelImpl(Day14Controller controller, Set<JavaPoint> rocks) {
        this.controller = controller;
        this.rocks = rocks;
        createNewSandPiece();
    }

    @Override
    public boolean isRock(JavaPoint p) {
        return rocks.contains(p);
    }

    @Override
    public boolean moveCurrentSandPiece() {
        return false;
    }

    @Override
    public void createNewSandPiece() {
        currentSandPiece = new JavaPoint(500, 0);
    }


}
