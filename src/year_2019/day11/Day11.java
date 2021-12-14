package year_2019.day11;

import year_2019.IntCodeComputer.IntCode;
import year_2019.IntCodeComputer.IntCodeAPI;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class Day11 {
    final static long[] DAY_10_PUZZLE_INPUT = new long[]{3, 8, 1005, 8, 320, 1106, 0, 11, 0, 0, 0, 104, 1, 104, 0, 3, 8, 102, -1, 8, 10, 101, 1, 10, 10, 4, 10, 1008, 8, 1, 10, 4, 10, 1001, 8, 0, 29, 2, 101, 10, 10, 3, 8, 102, -1, 8, 10, 1001, 10, 1, 10, 4, 10, 108, 1, 8, 10, 4, 10, 101, 0, 8, 54, 2, 3, 16, 10, 3, 8, 1002, 8, -1, 10, 101, 1, 10, 10, 4, 10, 1008, 8, 0, 10, 4, 10, 102, 1, 8, 81, 1006, 0, 75, 3, 8, 1002, 8, -1, 10, 1001, 10, 1, 10, 4, 10, 108, 0, 8, 10, 4, 10, 101, 0, 8, 105, 3, 8, 102, -1, 8, 10, 1001, 10, 1, 10, 4, 10, 1008, 8, 1, 10, 4, 10, 1001, 8, 0, 128, 3, 8, 1002, 8, -1, 10, 1001, 10, 1, 10, 4, 10, 108, 0, 8, 10, 4, 10, 102, 1, 8, 149, 1, 105, 5, 10, 1, 105, 20, 10, 3, 8, 102, -1, 8, 10, 101, 1, 10, 10, 4, 10, 108, 0, 8, 10, 4, 10, 1002, 8, 1, 179, 1, 101, 1, 10, 2, 109, 8, 10, 1006, 0, 74, 3, 8, 1002, 8, -1, 10, 101, 1, 10, 10, 4, 10, 1008, 8, 1, 10, 4, 10, 1001, 8, 0, 213, 1006, 0, 60, 2, 1105, 9, 10, 1, 1005, 11, 10, 3, 8, 1002, 8, -1, 10, 101, 1, 10, 10, 4, 10, 108, 1, 8, 10, 4, 10, 1002, 8, 1, 245, 1, 6, 20, 10, 1, 1103, 11, 10, 2, 6, 11, 10, 2, 1103, 0, 10, 3, 8, 1002, 8, -1, 10, 101, 1, 10, 10, 4, 10, 1008, 8, 0, 10, 4, 10, 1002, 8, 1, 284, 2, 1103, 12, 10, 2, 1104, 14, 10, 2, 1004, 12, 10, 2, 1009, 4, 10, 101, 1, 9, 9, 1007, 9, 968, 10, 1005, 10, 15, 99, 109, 642, 104, 0, 104, 1, 21102, 1, 48063419288L, 1, 21102, 1, 337, 0, 1105, 1, 441, 21101, 0, 846927340300L, 1, 21101, 0, 348, 0, 1105, 1, 441, 3, 10, 104, 0, 104, 1, 3, 10, 104, 0, 104, 0, 3, 10, 104, 0, 104, 1, 3, 10, 104, 0, 104, 1, 3, 10, 104, 0, 104, 0, 3, 10, 104, 0, 104, 1, 21102, 1, 235245104151L, 1, 21102, 395, 1, 0, 1105, 1, 441, 21102, 29032123584L, 1, 1, 21101, 0, 406, 0, 1105, 1, 441, 3, 10, 104, 0, 104, 0, 3, 10, 104, 0, 104, 0, 21101, 0, 709047878500L, 1, 21101, 429, 0, 0, 1106, 0, 441, 21101, 868402070284L, 0, 1, 21102, 1, 440, 0, 1105, 1, 441, 99, 109, 2, 22102, 1, -1, 1, 21101, 40, 0, 2, 21101, 0, 472, 3, 21102, 462, 1, 0, 1105, 1, 505, 109, -2, 2106, 0, 0, 0, 1, 0, 0, 1, 109, 2, 3, 10, 204, -1, 1001, 467, 468, 483, 4, 0, 1001, 467, 1, 467, 108, 4, 467, 10, 1006, 10, 499, 1102, 1, 0, 467, 109, -2, 2106, 0, 0, 0, 109, 4, 2101, 0, -1, 504, 1207, -3, 0, 10, 1006, 10, 522, 21101, 0, 0, -3, 22101, 0, -3, 1, 21202, -2, 1, 2, 21101, 1, 0, 3, 21102, 541, 1, 0, 1106, 0, 546, 109, -4, 2106, 0, 0, 109, 5, 1207, -3, 1, 10, 1006, 10, 569, 2207, -4, -2, 10, 1006, 10, 569, 21202, -4, 1, -4, 1105, 1, 637, 22102, 1, -4, 1, 21201, -3, -1, 2, 21202, -2, 2, 3, 21101, 588, 0, 0, 1105, 1, 546, 22102, 1, 1, -4, 21101, 0, 1, -1, 2207, -4, -2, 10, 1006, 10, 607, 21101, 0, 0, -1, 22202, -2, -1, -2, 2107, 0, -3, 10, 1006, 10, 629, 21201, -1, 0, 1, 21102, 629, 1, 0, 106, 0, 504, 21202, -2, -1, -2, 22201, -4, -2, -4, 109, -5, 2105, 1, 0};
    final static long WHITE = 1L;
    final static long BLACK = 0L;
    final HullPainterModel hullPainterModel = new HullPainterModel();
    Day11Hull view = new Day11Hull(this);
    BlockingQueue<Long> statusAtPoint = new LinkedBlockingQueue<>();
    BlockingQueue<Long> outputs = new LinkedBlockingQueue<>();
    IntCodeAPI brainApi = new IntCodeAPI(new IntCode(DAY_10_PUZZLE_INPUT, statusAtPoint, outputs), statusAtPoint, outputs);
    int unique_panels_painted = 0;

    Day11(){
        view.setDroid(hullPainterModel.robot);
        colorPoint(WHITE);
        inputCurrentColor();
        brainApi.startProgram();
    }

    public static <T> Color hullPaintingColorFunction(T value) {
        if (value != null) {
            if (value.equals(1L)) {
                return Color.WHITE;
            } else {
                return Color.GRAY;
            }
        } else {
            return Color.GRAY;
        }
    }

    private void inputCurrentColor() {
        brainApi.sendInput(hullPainterModel.getColorAtCurrentPoint());
    }

    /**
     * Executes one paint-rotate pair of steps if one is remaining.
     * @return whether a paint-rotate pair was remaining
     */
    public boolean executeOneStep() throws InterruptedException {
        Optional<Long> paintInstruction;
        if ((paintInstruction = brainApi.waitForOutput()).isPresent()) {
            colorPoint(paintInstruction.get());
            long rotationInstruction = outputs.take();
            rotateRobot(rotationInstruction);
            moveRobotForward();
            inputCurrentColor();
        }
        return paintInstruction.isPresent();
    }

    private void rotateRobot(long rotationInstruction) {
        hullPainterModel.rotateRobot(rotationInstruction);
        view.updateRobot();
    }

    private void moveRobotForward() {
        hullPainterModel.robot.moveForward();
        view.updateRobot();
    }

    private void colorPoint(Long paint) {
        if (!(hullPainterModel.painted.getOrDefault(hullPainterModel.robot.position, false))) {
            unique_panels_painted += 1;
        }
        hullPainterModel.paintPoint(paint);
        view.setUniquePanelsPainted(unique_panels_painted);
        view.setColor(hullPainterModel.robot.position, hullPaintingColorFunction(paint));

    }

    public void autopilot() throws InterruptedException {
        while (executeOneStep()){
            //TimeUnit.MILLISECONDS.sleep(20); // for visual only
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        new Day11();

    }
}
