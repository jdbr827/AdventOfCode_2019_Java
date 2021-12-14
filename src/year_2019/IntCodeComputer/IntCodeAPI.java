package year_2019.IntCodeComputer;

import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class IntCodeAPI {

    IntCode brain;
    BlockingQueue<Long> inputs;
    BlockingQueue<Long> outputs;


    public IntCodeAPI(IntCode brain) {
        this.brain = brain;
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
