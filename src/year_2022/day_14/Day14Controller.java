package year_2022.day_14;

import viewModelUtil.JavaPoint;

public class Day14Controller {
    Day14View view;
    Day14Model model;

    Day14Controller() {
        view = new Day14View(this);
        model = new Day14ModelImpl(this, new Day14Scanner("src/year_2022/day_14/test/day_14_sample_input.txt").readInRocks());
    }

    public static void main(String[] args) {
        new Day14Controller();
    }


    public boolean isRock(JavaPoint javaPoint) {
        return model.isRock(javaPoint);
    }

    public void putRock(JavaPoint rock) {
        view.putRock(rock);
    }
}
