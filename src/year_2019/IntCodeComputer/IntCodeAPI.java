package year_2019.IntCodeComputer;

import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class IntCodeAPI {

    private IntCode brain;
    private BlockingQueue<Long> inputs;
    private BlockingQueue<Long> outputs;


    public IntCodeAPI(long[] tape, BlockingQueue<Long> inputs, BlockingQueue<Long> outputs) {
        this.brain = new IntCode(tape, inputs, outputs);
        this.inputs = brain.input;
        this.outputs = brain.output;
    }


    public void startProgram() {
        brain.start();
    }

    public void sendInput(Long input) {
        inputs.add(input);
    }

    public Optional<Long> waitForOutput() throws InterruptedException {
        Optional<Long> result = Optional.ofNullable(outputs.poll(2, TimeUnit.SECONDS));
        while(!result.isPresent()) {
                if (!brain.isAlive()) {return Optional.empty();}
               result = Optional.ofNullable(outputs.poll(2, TimeUnit.SECONDS));
            }
        return result;
    }
}
