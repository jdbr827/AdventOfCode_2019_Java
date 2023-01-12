package year_2022.day_14;

import viewModelUtil.JavaPoint;

import java.util.concurrent.TimeUnit;

public class Day14Controller {
    Day14View view;
    Day14Model model;

    Day14Controller() {
         model = new Day14ModelImpl(this, new Day14Scanner("src/year_2022/day_14/test/day_14_sample_input.txt").readInRocks());
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
            model.createNewSandPiece();
        }
        view.repaint();
    }

    public JavaPoint getCurrentSandPiece() {
        return model.getCurrentSandPiece();

    }

    public boolean isAtRest(JavaPoint javaPoint) {
        return model.isAtRest(javaPoint);
    }

    public boolean sandInAbyss() {
        return model.sandInAbyss();
    }

    public void autoPilot() throws InterruptedException {
        while (!sandInAbyss()){
            executeOneTimeStep();
            TimeUnit.MILLISECONDS.sleep(20);
        }
    }
}
