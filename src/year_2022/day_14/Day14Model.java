package year_2022.day_14;

import lombok.Getter;
import lombok.Setter;
import viewModelUtil.JavaPoint;

import java.util.Set;

public interface Day14Model {
    boolean isRock(JavaPoint p);
    Day14Controller getController();

}

class Day14ModelImpl implements Day14Model {
    Set<JavaPoint> rockSet;
    @Getter
    Day14Controller controller;


    public Day14ModelImpl(Day14Controller controller, Set<JavaPoint> rockSet) {
        this.controller = controller;
        this.rockSet = rockSet;
        for (JavaPoint rock : rockSet) {
            controller.putRock(rock);
        }
    }

    @Override
    public boolean isRock(JavaPoint p) {
        return rockSet.contains(p);
    }


}
