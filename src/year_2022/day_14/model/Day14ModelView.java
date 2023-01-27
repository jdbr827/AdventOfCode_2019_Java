package year_2022.day_14.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import viewModelUtil.JavaPoint;
import year_2022.day_14.Day14Controller;
import year_2022.day_14.IDay14Controller;

public class Day14ModelView {

    IDay14Controller controller;
    @Setter
    Day14DataModel dataModel;

    public Day14ModelView(IDay14Controller controller) {
        this.controller = controller;
    }



    public void setToFalling(JavaPoint javaPoint) {
        dataModel.setToFalling(javaPoint);
        controller.setToFalling(javaPoint);
    }

    public void setToAtRest(JavaPoint javaPoint) {
        dataModel.setToAtRest(javaPoint);
        controller.setToRest(javaPoint);
    }


    public boolean getIsRock(JavaPoint p) {
        return dataModel.getIsRock(p);
    }

    public boolean getIsAtRest(JavaPoint p) {
        return dataModel.getIsAtRest(p);
    }

    public boolean getIsFloor(JavaPoint p) {
        return dataModel.getIsFloor(p);
    }
}
