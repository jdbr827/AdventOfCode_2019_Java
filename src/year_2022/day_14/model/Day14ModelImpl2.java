package year_2022.day_14.model;

import viewModelUtil.JavaPoint;

import java.util.Set;

class Day14ModelImpl2 extends Day14ModelImpl {


    public Day14ModelImpl2(Day14ModelView modelView, Set<JavaPoint> rocks) {
        super(modelView, rocks);
    }

    @Override
    public int floorY() {
        return lowestRockY + 2;
    }

    @Override
    public boolean isRock(JavaPoint p) {
        return day14ModelView.getIsRock(p) || day14ModelView.getIsFloor(p);
    }

    @Override
    public boolean endCondition() {
        return isAtRest(SPAWN_POINT);
    }

}
