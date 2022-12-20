package year_2022.day_05;

import lombok.AllArgsConstructor;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
public class Day5 {
    List<Stack<Character>> boat;
    List<Day5Instruction> instructions;

    public static String part1(String fileName) throws FileNotFoundException {
        return solveDay5(fileName, 9000);
    }

    public static String part2(String fileName) throws FileNotFoundException {
        return solveDay5(fileName, 9001);
    }

    public static String solveDay5(String fileName, int crateMoverMethod) throws FileNotFoundException {
        Day5Scanner scanner = new Day5Scanner(fileName);
        Day5 day5 = scanner.createStacks();
        day5.executeInstructions(crateMoverMethod);
        return day5.getTopString();

    }

    private void executeInstructions(int crateMoverMethod) {
        Consumer<Day5Instruction> crateMoverFunction = crateMoverMethod == 9000
                ? this::executeInstructionCrateMover1
                : this::executeInstructionCrateMover2;

        for (Day5Instruction instruction : instructions) {
            crateMoverFunction.accept(instruction);
        }
    }

    private void executeInstructionCrateMover2(Day5Instruction instruction) {
        Stack<Character> buffer = new Stack<>();
        for (int i=0; i<instruction.amountToMove; i++) {
            buffer.push(boat.get(instruction.moveFrom).pop());
        }
        for (int i=0; i<instruction.amountToMove; i++) {
            boat.get(instruction.moveTo).push(buffer.pop());
        }
    }

    private void executeInstructionCrateMover1(Day5Instruction instruction) {
        for (int i=0; i<instruction.amountToMove; i++) {
            boat.get(instruction.moveTo).push(boat.get(instruction.moveFrom).pop());
        }
    }



    private String getTopString() {
        StringBuilder s = new StringBuilder();
        for (int i=1; i<boat.size(); i++) {
            s.append(boat.get(i).peek());
        }
        return s.toString();
    }
}
