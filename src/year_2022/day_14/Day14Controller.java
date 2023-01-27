package year_2022.day_14;

import viewModelUtil.JavaPoint;
import year_2022.day_14.model.Day14ModelView;
import year_2022.day_14.model.Day14Model;
import year_2022.day_14.model.PointState;
import year_2022.day_14.view.Day14View;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Day14Controller implements IDay14Controller {
    Day14View view;
    Day14Model model;

    public Day14Controller(String fileName, int version) {
        Set<JavaPoint> rocks = new Day14Scanner(fileName).readInRocks();
        model = Day14Model.fromCornerRocksFile(fileName, version, new Day14ModelView(this));
        view = new Day14View(this);
        rocks.forEach(view::putRock);
        view.setFloor(model.floorY());
    }


    public void executeOneTimeStep() {
        model.executeOneTimeStep();
        view.repaint();
    }


    public boolean endCondition() {
        return model.endCondition();
    }

    public void autoPilot() throws InterruptedException {
        while (!endCondition()){
            executeOneTimeStep();
            TimeUnit.MILLISECONDS.sleep(20);
        }
    }

    public int getSandPiecesSoFar() {
        return model.getNumSandPiecesFallenSoFar();
    }



    public void setToFalling(JavaPoint javaPoint) {
        view.noteUpdate(javaPoint);
    }

    public void setToRest(JavaPoint javaPoint) {
        view.noteUpdate(javaPoint);
    }

    @Override
    public void noteUpdate(JavaPoint javaPoint) {
        view.noteUpdate(javaPoint);
    }

    @Override
    public void setToOpen(JavaPoint p) {
        view.noteUpdate(p);
    }

    public PointState getStateOfPoint(JavaPoint javaPoint) {
        return model.getStateOfPoint(javaPoint);
    }
}
