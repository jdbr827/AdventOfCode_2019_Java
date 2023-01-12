package year_2022.day_14;

import viewModelUtil.JavaPoint;

public interface Day14Model {
    boolean isRock(JavaPoint p);
    boolean isSand(JavaPoint p);

}

class Day14ModelImpl implements Day14Model {

    @Override
    public boolean isRock(JavaPoint p) {
        return p.x == 500;
    }


}
