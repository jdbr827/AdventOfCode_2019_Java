package year_2022.day_05;

import com.google.common.base.Splitter;
import utils.ReadIn;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day5Scanner {
    Scanner scanner;

    static final Pattern cratesPattern = Pattern.compile("\\[([A-Z])]");
    static final Pattern numbersPattern = Pattern.compile("\\s([0-9]+)\\s");
    static final Pattern instructionsPattern = Pattern.compile("move ([0-9]+) from ([0-9]+) to ([0-9]+)");

    public Day5Scanner(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        scanner = new Scanner(file);
    }

    public List<Character> getNextLine() {
        String line = scanner.nextLine();
        //System.out.println(line);
        List<String> splitLine = Splitter.fixedLength(4).splitToList(line);
        //System.out.println(splitLine.get(0).charAt(1));
        if (splitLine.get(0).charAt(1) == '1') {
            return List.of();
        }

        return splitLine.stream().map((crate) -> {
            if (crate.trim().isEmpty()) {
                return '7';
            } else {
                Matcher m = cratesPattern.matcher(crate);
                ReadIn.findOrElseThrow(m, "Could not match ranges pattern");
                return m.group(1).charAt(0);
            }
        }).collect(Collectors.toList());
    }

    public Day5 createStacks() {
        Stack<String> lines = new Stack<>();
        String thisLine = "";
        while (!(thisLine = scanner.nextLine()).equals("")) {
            lines.push(thisLine);
            //System.out.println(thisLine);
        }
        String numbers = lines.pop();
        List<Stack<Character>> boat = new ArrayList<>();
        boat.add(new Stack<>()); // empty for off-by-ones;
        for (int i=1; i<numbers.length(); i+=4) {
            boat.add(new Stack<>());
            //System.out.println(Character.getNumericValue(numbers.charAt(i)));
        }

        while (!lines.isEmpty()) {
            String processableLine = lines.pop();
            for (int i=0; 4*i+1<processableLine.length(); i+=1) {
                char thisChar = processableLine.charAt(4*i + 1);
                if (thisChar != ' ') {
                    boat.get(i+1).push(thisChar);
                }
            }
        }
        // made boat

        List<Day5Instruction> instructions = new LinkedList<>();
        while (scanner.hasNextLine()) {
            thisLine = scanner.nextLine();
            Matcher m = instructionsPattern.matcher(thisLine);
            ReadIn.findOrElseThrow(m, "Could not match instructions pattern");
            instructions.add(new Day5Instruction(
                    Integer.parseInt(m.group(1)),
                    Integer.parseInt(m.group(2)),
                    Integer.parseInt(m.group(3))
                    ));
        }

        return new Day5(boat, instructions);



    }
}
