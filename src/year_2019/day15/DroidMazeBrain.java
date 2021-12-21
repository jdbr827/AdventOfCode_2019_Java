package year_2019.day15;

import year_2019.IntCodeComputer.IntCodeAPI;

public class DroidMazeBrain extends IntCodeAPI {

    public DroidMazeBrain(long[] tape) {
        super(tape);
    }

    public DroidMazeOutputInstruction getNextOutputInstruction() throws InterruptedException {
        return DroidMazeOutputInstruction.valueOf(waitForOutputKnown());

    }
}
