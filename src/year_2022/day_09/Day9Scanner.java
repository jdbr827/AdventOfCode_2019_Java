package year_2022.day_09;

import javafx.util.Pair;
import utils.AOCScanner;
import utils.ReadIn;

import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day9Scanner extends AOCScanner  {
    static final Pattern rangesPattern = Pattern.compile("([RULD]) ([0-9]+)*");


    public Day9Scanner(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    public Day9InstructionPair getNextInstruction() {
        if (scanner.hasNextLine()) {
            Matcher m = rangesPattern.matcher(scanner.nextLine());
            ReadIn.findOrElseThrow(m, "Could not match ranges pattern");
            return new Day9InstructionPair(
                    m.group(1),
                    Integer.parseInt(m.group(2))
            );
        }
        return null;
    }
}
