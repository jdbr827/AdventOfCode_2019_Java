package year_2022.day_14;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import viewModelUtil.JavaPoint;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

class Day14ModelImpl2 extends Day14ModelImpl {


    public Day14ModelImpl2(Set<JavaPoint> rocks) {
        super(rocks);
    }

    @Override
    public boolean isRock(JavaPoint p) {
        return rocks.contains(p) || p.y == lowestRockY + 2;
    }

    @Override
    public boolean isAtRest(JavaPoint p) {
        return piecesAtRest.contains(p) || isRock(p);
    }

    @Override
    public boolean endCondition() {
        return isAtRest(SPAWN_POINT);
    }

}
