package year_2019.day15.model;

import year_2019.IntCodeComputer.IntCodeAPI;

public class DroidMazeBrain extends IntCodeAPI {

    public DroidMazeBrain(long[] tape) {
        super(tape);
    }

    private DroidMazeOutputInstruction getNextOutputInstruction() throws InterruptedException {
        return DroidMazeOutputInstruction.valueOf(waitForOutputKnown());

    }

    public DroidMazeOutputInstruction attemptDroidMove(CardinalDirection direction) throws InterruptedException {
        sendInput(direction.inputInstruction);
        return getNextOutputInstruction();
    }
}
