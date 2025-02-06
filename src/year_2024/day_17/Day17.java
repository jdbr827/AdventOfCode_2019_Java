package year_2024.day_17;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import utils.AOCScanner;
import utils.ReadIn;
import year_2024.day_14.Day14;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
public class Day17 {
    @NotNull Integer initialRegisterA;
    @NotNull Integer initialRegisterB;
    @NotNull Integer initialRegisterC;
    @NotNull List<Integer> program;


    @RequiredArgsConstructor
    public class ProgramExecution {
        int instructionPointer = 0;
        @NotNull Integer registerA;
        @NotNull Integer registerB;
        @NotNull Integer registerC;
        List<Integer> output = new LinkedList<>();

        Map<Integer, Consumer<Integer>> operatorMap = Map.of(
                0, this::divisionA,
                1, this::bxl,
                2, this::bst,
                3, this::jnz,
                4, this::bxc,
                5, this::out,
                6, this::bdv,
                7, this::cdv
        );


        public Integer processComboOperand(Integer operand) {
            if (operand < 4) {
                return operand;
            }
            if (operand == 4) {
                return registerA;
            }
            if (operand == 5) {
                return registerB;
            }
            if (operand == 6) {
                return registerC;
            }
            return -1;
        }

        private void incrementInstructionPointer() {
            instructionPointer += 2;
        }

        public void divisionA(int operand) {
            registerA /= (int) Math.pow(2, processComboOperand(operand));
            incrementInstructionPointer();
        }

        public void bxl(Integer operand) {
            registerB ^= operand;
            incrementInstructionPointer();
        }

        public void bst(Integer operand) {
            registerB = processComboOperand(operand) % 8;
            incrementInstructionPointer();
        }

        public void jnz(Integer operand) {
            if (registerA == 0) {
                incrementInstructionPointer();
            } else {
                instructionPointer = operand;
            }
        }

        public void bxc(Integer operand) {
            registerB = registerB ^ registerC;
            incrementInstructionPointer();
        }

        public void out(Integer operand) {
            output.add(processComboOperand(operand) % 8);
            incrementInstructionPointer();
        }

        public void bdv(Integer operand) {
            registerB = registerA / (int) Math.pow(2, processComboOperand(operand));
            incrementInstructionPointer();
        }

        public void cdv(Integer operand) {
            registerC = registerA / (int) Math.pow(2, processComboOperand(operand));
            incrementInstructionPointer();
        }

        private void doOperation() {
            int opCode = program.get(instructionPointer);
            int operand = program.get(instructionPointer + 1);
            operatorMap.get(opCode).accept(operand);
        }

        public List<Integer> run() {
            while (instructionPointer < program.toArray().length) {
                doOperation();
            }
            return output;
        }

        public boolean runningCreatesInitialProgram() {
            while (instructionPointer < program.size() && program.subList(0, output.size()).equals(output)) {
                doOperation();
            }
            return program.equals(output);
        }
    }


    public static Day17 fromFile(String inputFilename) {
        return new Day17Scanner(inputFilename).scan();
    }

    public String getOutputAfterRunning() {
        return printableOutput(new ProgramExecution(initialRegisterA, initialRegisterB, initialRegisterC).run());
    }

    private String printableOutput(List<Integer> output) {
        if (output.isEmpty()) {
            return "";
        }

        StringBuilder msg = new StringBuilder();
        msg.append(output.getFirst());
        for (int i = 1; i < output.size(); i++) {
            msg.append(",").append(output.get(i));
        }
        return msg.toString();
    }


    // Is the "output" monotonically increasing?
    public int lowestAToOutputCopy() {
        int initialA;
        String last = "0";
        for (initialA = 0; !(new ProgramExecution(initialA, initialRegisterB, initialRegisterC).runningCreatesInitialProgram()); initialA++) {
//            if (printableOutput(new ProgramExecution(initialA, initialRegisterB, initialRegisterC).run()).compareTo(last) > 0) {
//                System.out.println("HI");
//            }

            last = printableOutput(new ProgramExecution(initialA, initialRegisterB,initialRegisterC).run());
            System.out.println(last);
        }
            ;
        return initialA;
    }
}
