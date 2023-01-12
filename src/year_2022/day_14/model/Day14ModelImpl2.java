package year_2022.day_14.model;

import viewModelUtil.JavaPoint;

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
    public boolean endCondition() {
        return isAtRest(SPAWN_POINT);
    }

}
