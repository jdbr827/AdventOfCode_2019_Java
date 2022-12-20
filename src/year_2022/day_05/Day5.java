package year_2022.day_05;

import lombok.AllArgsConstructor;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

@AllArgsConstructor
public class Day5 {
    List<Stack<Character>> boat;
    List<Day5Instruction> instructions;

    public static String part1(String fileName) throws FileNotFoundException {
        Day5Scanner scanner = new Day5Scanner(fileName);
        Day5 day5 = scanner.createStacks();
        day5.executeInstructions();
        return day5.getTopString();

    }

    private String getTopString() {
        StringBuilder s = new StringBuilder();
        for (int i=1; i<boat.size(); i++) {
            s.append(boat.get(i).peek());
        }
        return s.toString();
    }

    private void executeInstructions() {
    }
}
