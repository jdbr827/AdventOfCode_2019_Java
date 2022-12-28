package year_2022.day_10;

import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Queue;
import java.util.function.Function;

@NoArgsConstructor
public class Day10CPU {
    int register = 1;
    Queue<Day10Operation> operations;
    Day10Operation currentOperation;
    int cycle = 1;
    int verbose = Integer.MAX_VALUE;
    int signalStrengthSum = 0;

    void executeCycle() {
        logIfVerbose(2, "Beginning cycle " + cycle);
        if (currentOperation == null) {
            currentOperation = operations.remove();
            logIfVerbose(2, "Instruction " + currentOperation.name + " begins executing");
        }
        logIfVerbose(2, "During cycle " + cycle + ", X=" + register);
        if (Math.floorMod(cycle, 40) == 20) {
            logIfVerbose(3, "During cycle " + cycle + ", X=" + register + ", so signal strength is " + cycle + " * " + register + " = " + (cycle * register));
            signalStrengthSum += cycle * register;
        }
        currentOperation.timeToExecute--;
        if (currentOperation.timeToExecute == 0) {
            register = currentOperation.operation.apply(register);
            logIfVerbose(2, currentOperation.name + " finishes executing");
            currentOperation = null;
        }
        logIfVerbose(2, "After cycle " + cycle + ", X=" + register);
        cycle++;
        logIfVerbose(2, "------");
    }

    private void logIfVerbose(int verbose, String s) {
        if (this.verbose <= verbose) {
            System.out.println(s);
        }
    }

    public int begin() {
        return begin(Integer.MAX_VALUE);
    }

    public int begin(int verbose) {
        this.verbose = verbose;
        while (!(operations.isEmpty() && currentOperation == null)) {
            executeCycle();
        }
        return signalStrengthSum;
    }
}
