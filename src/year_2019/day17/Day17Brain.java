package year_2019.day17;

import lombok.AllArgsConstructor;
import year_2019.IntCodeComputer.AsciiIntCodeAPI;

@AllArgsConstructor
public class Day17Brain {
    Day17InputSource scanner;
    AsciiIntCodeAPI brain;

    private void inputNextLine() {
        brain.sendLine(scanner.getNextLine());
    }

    private void doInteraction() throws InterruptedException {
        inputNextLine();
        brain.getNextOutputsToString();
    }

    public Long runProgramAndGetDustCollected() throws InterruptedException {
        brain.startProgram();
        /* Initial String */
        brain.getNextOutputsToString();
        System.out.println(brain.getLastLongOutput());

        /* Main Movement Routine */
        doInteraction();

        /* Function A */
        doInteraction();

        /* Function B */
        doInteraction();

        /* Function C */
        doInteraction();

        /* Continuous Video Feed? */
        doInteraction();

        return brain.getLastLongOutput();
    }


}
