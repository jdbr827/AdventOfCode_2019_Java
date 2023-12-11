package year_2023.day_08;

import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class Day8 {
    Map<String, Map<Character, String>> desertMap;
    String instructions;

    int countStepsToZZZ() {
        String location = "AAA";
        int instructionIdx = 0;
        int nSteps = 0;
        while (!(location.equals("ZZZ"))) {
            location = desertMap.get(location).get(instructions.charAt(instructionIdx));
            instructionIdx = (instructionIdx + 1) % instructions.length();
            nSteps++;
        }
        return nSteps;
    }
}
