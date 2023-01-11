package year_2022.day_09;

import javafx.util.Pair;
import utils.AOCScanner;
import utils.ReadIn;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day9Scanner extends AOCScanner implements Iterator<Day9InstructionPair> {
    static final Pattern rangesPattern = Pattern.compile("([RULD]) ([0-9]+)*");


    public Day9Scanner(String fileName) {
        super(fileName);
    }

    private Day9InstructionPair getNextInstruction() {
        Matcher m = rangesPattern.matcher(scanner.nextLine());
        ReadIn.findOrElseThrow(m, "Could not match ranges pattern");
        return new Day9InstructionPair(
                m.group(1),
                Integer.parseInt(m.group(2))
        );
    }

    @Override
    public boolean hasNext() {
        return scanner.hasNextLine();
    }

    @Override
    public Day9InstructionPair next() {
        return getNextInstruction();
    }
}
