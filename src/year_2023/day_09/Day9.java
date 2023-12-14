package year_2023.day_09;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@AllArgsConstructor
public class Day9 {
    Collection<Day9Line> lines;

    public static int part1(String fileName) {
        return Day9Scanner.scan(fileName)
                .lines.stream()
                .map(Day9Line::generateNextValue)
                .reduce(0, Math::addExact);
    }

    public static int part2(String fileName) {
        return Day9Scanner.scan(fileName)
                .lines.stream()
                .map(Day9Line::generatePreviousValue)
                .reduce(0, Math::addExact);
    }
}



@AllArgsConstructor
@RequiredArgsConstructor
class Day9Line {
    @NotNull  List<Integer> line;
    List<Integer> leftmostElement = new ArrayList<>();
    List<Integer> rightmostElement;


    public int generateNextValue() {
        addAllElementsForProcessing();
        return rightmostElement.stream().reduce(0, Math::addExact);
    }

    public int generatePreviousValue() {
        addAllElementsForProcessing();
        return leftmostElement.stream().reduce(0, (agg, elm) -> elm - agg);
    }

    private void addAllElementsForProcessing() {
        rightmostElement = new ArrayList<>();
        for (Integer element : line) {
            processElement(element);
        }
    }

    private void processElement(int elm) {
        rightmostElement.add(0, elm);
        for (int j = 1; j < rightmostElement.size(); j++) {
            rightmostElement.set(j, rightmostElement.get(j-1) - rightmostElement.get(j));
        }
        leftmostElement.add(0, rightmostElement.get(rightmostElement.size() -1));
    }
}
