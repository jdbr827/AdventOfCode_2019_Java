package year_2022.day_14.model;

import lombok.Setter;
import viewModelUtil.JavaPoint;
import year_2022.day_14.IDay14Controller;
import year_2022.day_14.model.dataModel.Day14DataWriter;

public class Day14ModelView {

    IDay14Controller controller;
    @Setter public Day14DataWriter dataWriter;

    public Day14ModelView(IDay14Controller controller) {
        this.controller = controller;
    }


    public void setCurrentSandPiece(JavaPoint p) {
        dataWriter.setCurrentSandPiece(p);
    }

    public void setToFalling(JavaPoint javaPoint) {
        dataWriter.setToFalling(javaPoint);
        controller.setToFalling(javaPoint);
    }

    public void setToAtRest(JavaPoint javaPoint) {
        dataWriter.setToAtRest(javaPoint);
        controller.setToRest(javaPoint);
    }

    public void setToOpen(JavaPoint p) {
        dataWriter.setToOpen(p);
        controller.setToOpen(p);
    }
}
