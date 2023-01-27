package year_2022.day_14;

import viewModelUtil.JavaPoint;
import year_2022.day_14.model.Day14DataModelImpl2;
import year_2022.day_14.model.Day14Model;
import year_2022.day_14.model.PointState;
import year_2022.day_14.view.Day14View;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Day14Controller {
    Day14View view;
    Day14Model model;

    public Day14Controller(String fileName, int version) {
        Set<JavaPoint> rocks = new Day14Scanner(fileName).readInRocks();
        model = Day14Model.fromCornerRocksFile(fileName, version, new Day14DataModelImpl2(this));
        view = new Day14View(this);
        rocks.forEach(view::putRock);
        view.putRock(new JavaPoint(0, model.floorY()));
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
        view.setToFalling(javaPoint);
    }

    public void setToRest(JavaPoint javaPoint) {
        view.setToRest(javaPoint);
    }

    public PointState getStateOfPoint(JavaPoint javaPoint) {
        return model.getStateOfPoint(javaPoint);
    }
}
