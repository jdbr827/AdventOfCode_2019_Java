package year_2019.IntCodeComputer;

import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
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

    public IntCodeAPI(long[] tape, BlockingQueue<Long> inputs) {
        this(tape, inputs, new LinkedBlockingQueue<>());
    }

    public IntCodeAPI(long[] tape) {
        this(tape, new LinkedBlockingQueue<>(), new LinkedBlockingQueue<>());
    }


    public void startProgram() {
        brain.start();
    }

    public void sendInput(Long input) {
        inputs.add(input);
    }

    /**
     * Waits for the next output from the brain.
     * @return An empty Optional if the brain thread is dead, otherwise an optional of the next output.
     * @throws InterruptedException
     */
    public Optional<Long> waitForOutputOptional() throws InterruptedException {
        Optional<Long> result = Optional.ofNullable(outputs.poll(2, TimeUnit.SECONDS));
        while(!result.isPresent()) {
                if (!brain.isAlive()) {return Optional.empty();}
               result = Optional.ofNullable(outputs.poll(2, TimeUnit.SECONDS));
            }
        return result;
    }

    /**
     * Waits for the next output from the brain.
     * @return An empty Optional if the brain thread is dead or awaiting a new input,
     * otherwise an optional of the next output.
     * @throws InterruptedException
     */
    public Optional<Long> waitForOutputOptionalSuspended() throws InterruptedException {
        Optional<Long> result = Optional.ofNullable(outputs.poll(20, TimeUnit.MILLISECONDS));
        while(!result.isPresent()) {
                if (!brain.isAlive()) {return Optional.empty();}
                else if (isAwaitingNextInput()) {return Optional.empty();}
               result = Optional.ofNullable(outputs.poll(20, TimeUnit.MILLISECONDS));
            }
        return result;
    }

     public Optional<Long> waitForOutputOptional(Runnable inputProvider) throws InterruptedException {
        Optional<Long> result = Optional.ofNullable(outputs.poll(20, TimeUnit.MILLISECONDS));
        while(!result.isPresent()) {
                if (!brain.isAlive()) {return Optional.empty();}
                else if (isAwaitingNextInput()) {inputProvider.run();}
               result = Optional.ofNullable(outputs.poll(20, TimeUnit.MILLISECONDS));
            }
        return result;
    }

    public Long waitForOutputKnown() throws InterruptedException {
        return outputs.take();
    }


    private boolean isAwaitingNextInput() {
        return brain.getState() == Thread.State.WAITING && outputs.isEmpty();
    }

}
