package year_2022.day_22;

import utils.AOCScanner;
import utils.ReadIn;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day22Scanner extends AOCScanner {
    public Day22Scanner(String fileName) throws FileNotFoundException {
        super(fileName);
    }



    public List<String> readInDiagram() {
        List<String> diagram = new ArrayList<>();
        String thisLine;
        while (!(thisLine = scanner.nextLine()).equals("")) {
         diagram.add(thisLine);
        }
        return diagram;
    }



    public List<MonkeyInstruction> scanInstructions() {
        String instructionLine = scanner.nextLine();

        List<Integer> movements = Arrays.stream(instructionLine.split("R|L")).map(Integer::parseInt).collect(Collectors.toList());
        List<Character> rotations = new ArrayList<>();
        for (char c : instructionLine.toCharArray()) {
            if (c == 'R' || c == 'L') {
                rotations.add(c);
            }
        }
        System.out.println(movements);
        System.out.println(rotations);

        List<MonkeyInstruction> instructions = new ArrayList<>();
        int i = Math.min(movements.size(), rotations.size());
        int j = Math.max(movements.size(), rotations.size());
        for (int k=0 ; k<i; k++) {
            instructions.add(new MonkeyInstruction(movements.get(k), rotations.get(k)));
        }
        if (movements.size() > rotations.size()) {
            for (int k=i; k<j; k++) {
                instructions.add(new MonkeyInstruction(movements.get(k), null));
            }
        } else {
            for (int k=i; k<j; k++) {
                instructions.add(new MonkeyInstruction(0, rotations.get(k)));
            }
        }

        return instructions;



    };
}
