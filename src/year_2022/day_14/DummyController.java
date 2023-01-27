package year_2022.day_14;

import viewModelUtil.JavaPoint;
import year_2022.day_14.model.PointState;

public class DummyController implements IDay14Controller {

    @Override
    public PointState getStateOfPoint(JavaPoint javaPoint) {
        return null;
    }

    @Override
    public void autoPilot() throws InterruptedException {
    }

    @Override
    public int getSandPiecesSoFar() {
        return 0;
    }

    @Override
    public void setToFalling(JavaPoint javaPoint) {
    }

    @Override
    public void setToRest(JavaPoint javaPoint) {

    }
}
