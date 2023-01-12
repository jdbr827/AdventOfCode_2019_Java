package year_2022.day_14;

import viewModelUtil.JavaPoint;

import java.util.concurrent.TimeUnit;

public class Day14Controller implements IDay14Controller {
    Day14View view;
    Day14Model model;

    Day14Controller() {
         model = Day14Model.fromCornerRocksFile("src/year_2022/day_14/test/day_14_sample_input.txt", 2);
         view = new Day14View(this);
         addRocksToView();
    }

    private void addRocksToView() {
        model.getRocks().forEach(view::putRock);
    }

    public static void main(String[] args) {
        new Day14Controller();
    }



    public boolean isRock(JavaPoint javaPoint) {
        return model.isRock(javaPoint);
    }

    public void executeOneTimeStep() {
        if (!model.moveCurrentSandPiece()) {
            view.setSandPiecesSoFar(model.getSandPiecesSoFar());
        }
        view.repaint();
    }

    public JavaPoint getCurrentSandPiece() {
        return model.getCurrentSandPiece();
    }


    public boolean isAtRest(JavaPoint javaPoint) {
        return model.isAtRest(javaPoint);
    }

    public boolean endCondition() {
        return model.endCondition();
    }

    public void autoPilot() throws InterruptedException {
        while (!endCondition()){
            executeOneTimeStep();
            view.setCurrentSandPiece(model.getCurrentSandPiece());
            TimeUnit.MILLISECONDS.sleep(20);
        }
    }

    public boolean isCurrentSandPiece(JavaPoint javaPoint) {
        return javaPoint.equals(model.getCurrentSandPiece());
    }
}
