package year_2022.day_05;

import com.google.common.base.Splitter;
import utils.AOCScanner;
import utils.ReadIn;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day5Scanner extends AOCScanner {

    static final Pattern instructionsPattern = Pattern.compile("move ([0-9]+) from ([0-9]+) to ([0-9]+)");

    public Day5Scanner(String fileName) throws FileNotFoundException {
        super(fileName);
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
