package year_2022.day_14.model;

import viewModelUtil.JavaPoint;

public interface Day14ModelPartConstraint {
    static Day14ModelPartConstraint createNew(int part, Day14DataModel dataModel) {

        switch(part) {
            case 1:
                return new Day14ModelPartConstraint1(dataModel);
            case 2:
                return new Day14ModelPartConstraint2(dataModel);
            default:
                return null;
        }
    }

    boolean endCondition();

    int floorY();

    boolean allowsSand(JavaPoint javaPoint);
}



